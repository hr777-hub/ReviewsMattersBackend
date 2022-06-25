package com.example.ReviewsMattersBackend.service;

import com.example.ReviewsMattersBackend.dto.RegistrationRequestDto;
import com.example.ReviewsMattersBackend.entities.ConfirmationToken;
import com.example.ReviewsMattersBackend.email.EmailValidator;
import com.example.ReviewsMattersBackend.entities.User;
import com.example.ReviewsMattersBackend.dao.UserRepository;
import com.example.ReviewsMattersBackend.config.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {


    private ConfirmationTokenService confirmationTokenService;
    private UserService userService;
    private EmailValidator emailValidator;
    private UserRepository userRepository;


    public String register(RegistrationRequestDto request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
        return "Account activation link send to your email please confirm your account!";
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        userService.enableUser(confirmationToken.getUser());
        return "confirmed";
    }
    @Transactional
    public User getCurrentUser() {
        Authentication principal =  SecurityContextHolder.
                getContext().getAuthentication();
        return userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getName()));
    }
}
