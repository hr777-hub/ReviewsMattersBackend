package com.example.ReviewsMattersBackend.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Service
public class RegistrationRequestDto {
    private String username;
    private String email;
    private String password;
}
