package fr.dodoconvert.controller;

import fr.dodoconvert.authentication.IAuthenticationService;
import fr.dodoconvert.dto.AccountResponseDTO;
import fr.dodoconvert.dto.LoginRequestDTO;
import fr.dodoconvert.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AccountResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return authenticationService.login(request);
    }




}