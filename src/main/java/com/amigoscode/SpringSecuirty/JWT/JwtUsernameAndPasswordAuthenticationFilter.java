package com.amigoscode.SpringSecuirty.JWT;

import java.util.Date;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//! Verify username and password (Credentials) and generate JWT token
//! SEND CREDENTIALS TO /authenticate
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws org.springframework.security.core.AuthenticationException {
        try {
            UsernameAndPasswordAuthRequest authenticationRequest = new ObjectMapper()
                    .readValue(
                            request.getInputStream(),
                            UsernameAndPasswordAuthRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword());

            return authenticationManager.authenticate(authentication);

        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        // Jwts.builder(t -> {
        // t.setSubject(authResult.getName());
        // t.claim("authorities", authResult.getAuthorities());
        // t.setIssuedAt(new Date());
        // t.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        // }).signWith(Keys.hmacShaKeyFor("anaGAMEDawyyy".getBytes()));

        String secretKey = "yourSecretKey";
        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
