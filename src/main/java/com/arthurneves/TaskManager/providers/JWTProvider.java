package com.arthurneves.TaskManager.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {
    @Value("${security.token}")
    private String secret;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject(); // Se tudo ocorrer bem, ele ira retornar o payload para nos
        } catch (JWTVerificationException e) {
            return "";
        }
    }
}
