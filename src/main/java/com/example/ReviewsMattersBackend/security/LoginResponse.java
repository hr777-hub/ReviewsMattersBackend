package com.example.ReviewsMattersBackend.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String access_token;
    private String refresh_token;
    private String username;
}
