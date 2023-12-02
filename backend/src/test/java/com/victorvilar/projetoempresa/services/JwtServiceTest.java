package com.victorvilar.projetoempresa.services;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Customer service tests class")
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    private static String token;

    @Test
    @DisplayName("generate a token")
    public void generateToken_WhenSuccessfull(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("username","teste");
        claims.put("role","testUser");
        String token = this.jwtService.generateJwtToken(claims);
        this.token = token;
        Assertions.assertNotNull(token);

    }

    @Test
    @DisplayName("validate a token")
    public void validateToken_WhenSuccessfull(){
        Claims claims = this.jwtService.validateJwtToken(this.token);
        assertEquals(claims.get("role"), "testUser");
        assertEquals(claims.get("username"), "teste");

    }


}