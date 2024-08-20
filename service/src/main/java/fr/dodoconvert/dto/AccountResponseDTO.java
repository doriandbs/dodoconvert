package fr.dodoconvert.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDTO {
    private String message;
}