package com.example.ReviewsMattersBackend.controller;

import com.example.ReviewsMattersBackend.service.AuthService;
import com.example.ReviewsMattersBackend.dto.RegistrationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequestDto registrationRequestDto){
        return status(HttpStatus.CREATED).body(authService.register(registrationRequestDto));
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return authService.confirmToken(token);
    }

}
