package com.example.ReviewsMattersBackend.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationEmail {
    private String subject;
    private String recipient;
    private String body;
}
