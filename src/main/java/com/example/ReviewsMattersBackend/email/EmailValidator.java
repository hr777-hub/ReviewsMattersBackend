package com.example.ReviewsMattersBackend.email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        //TODO: regex for email validation
        return true;
    }
}
