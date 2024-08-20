package fr.dodoconvert.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String firstname;
    private String lastname;
    private String password;
    private String email;
}