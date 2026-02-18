package com.arthurneves.TaskManager.security;

import com.arthurneves.TaskManager.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if (header != null) {
            String payload = jwtProvider.validateToken(header);

            // Authorization vazio
            if (payload.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(payload, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
