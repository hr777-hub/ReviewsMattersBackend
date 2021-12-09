package com.example.ReviewsMattersBackend.user;

import com.example.ReviewsMattersBackend.auth.token.ConfirmationToken;
import com.example.ReviewsMattersBackend.auth.token.ConfirmationTokenService;
import com.example.ReviewsMattersBackend.email.MailService;
import com.example.ReviewsMattersBackend.email.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private MailService mailService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;
    private final String USER_NOT_FOUND = "user with User Name %s not found";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
    }
    @Transactional
    public void signUpUser(User user){
        boolean userNameExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (userNameExists) {
            throw new IllegalStateException("user name already taken");
        }

        boolean userEmailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if(userEmailExists){
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        mailService.sendMail(new NotificationEmail(
                "please activate your account through email",
                user.getEmail(),
                "thank you for signup on reviewsmatters click link below to activate your account "+
                        "http://localhost:8080/api/v1/auth/confirm?token="+token
        ));
    }
    public void enableUser(User user){
        User user1 = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        user1.setEnable(true);
    }


}
