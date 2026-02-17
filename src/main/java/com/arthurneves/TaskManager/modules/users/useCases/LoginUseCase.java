package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.exceptions.EmailNotFoundException;
import com.arthurneves.TaskManager.exceptions.WrongPasswordException;
import com.arthurneves.TaskManager.modules.users.dto.UserLoginRequestDTO;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    @Value("${security.token}")
    private String secret;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(UserLoginRequestDTO data) {
        // Verificando se email existe
        UserEntity user = repository.findByEmail(data.email()).orElseThrow(() -> {
            throw new EmailNotFoundException();
        });

        // Verificando senhas
        boolean passwordMatches = passwordEncoder.matches(data.password(), user.getPassword());
        if (!passwordMatches) {
            throw new WrongPasswordException();
        }

        // Gerando JWT
        Algorithm algorith = Algorithm.HMAC256(secret); // Criptografia do token
        String token = JWT.create().withIssuer("Arthur A.") // issuer -> emissor
                .withSubject(user.getId().toString()) // id do dono do token
                .sign(algorith); // Passando algoritmo

        return token;
    }
}
