package com.arthurneves.TaskManager.utils;

import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserIdFromJWToken {
    @Autowired
    private JWTProvider jwtProvider;

    public UUID get(String token) {
        return UUID.fromString(this.jwtProvider.validateToken(token));
    }
}
