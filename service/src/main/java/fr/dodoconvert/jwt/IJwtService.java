package fr.dodoconvert.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String extractUsername(String jwt);
    boolean isTokenValid(String jwt, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
}
