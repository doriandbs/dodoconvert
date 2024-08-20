package fr.dodoconvert.authentication.impl;

import fr.dodoconvert.CustomPasswordEncoder;
import fr.dodoconvert.authentication.IAuthenticationService;
import fr.dodoconvert.dto.AccountResponseDTO;
import fr.dodoconvert.dto.LoginRequestDTO;
import fr.dodoconvert.dto.RegisterRequestDTO;
import fr.dodoconvert.entity.UserEntity;
import fr.dodoconvert.jwt.IJwtService;
import fr.dodoconvert.repository.IUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService{
    private final IUserJpaRepository userJpaRepository;

    private final CustomPasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final IJwtService jwtService;


    @Override
    public ResponseEntity<AccountResponseDTO> register(RegisterRequestDTO user) {
        if(userJpaRepository.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(AccountResponseDTO.builder()
                            .message("Un utilisateur existe déjà avec cette adresse email")
                            .build());
        }
        UserEntity newUser = UserEntity.builder()
                .firstName(user.getFirstname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .lastName(user.getLastname())
                .build();
        userJpaRepository.save(newUser);
        return ResponseEntity.ok(AccountResponseDTO.builder().message("Utilisateur bien enregistré")
                .build());
    }

    @Override
    public ResponseEntity<AccountResponseDTO> login(LoginRequestDTO request) {
        var user = userJpaRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var jwtToken = jwtService.generateToken(user);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Acces-Control-Expose-Headers", "Authorization");
        responseHeaders.add("Authorization", "Bearer " + jwtToken);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(AccountResponseDTO.builder()
                        .message("Utilisateur authentifié avec succès").build());
    }
}