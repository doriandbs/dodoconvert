package fr.dodoconvert.authentication;

import fr.dodoconvert.dto.AccountResponseDTO;
import fr.dodoconvert.dto.LoginRequestDTO;
import fr.dodoconvert.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {
    ResponseEntity<AccountResponseDTO> register(RegisterRequestDTO user);

    ResponseEntity<AccountResponseDTO> login(LoginRequestDTO request);
}
