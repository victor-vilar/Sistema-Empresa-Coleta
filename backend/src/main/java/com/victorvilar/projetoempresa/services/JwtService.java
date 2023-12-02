package com.victorvilar.projetoempresa.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * Jwt service to create jwt tokens e validate jwt tokens.
 * Used with JwtTokenValidatorFilter and JwtTokenGeneratorFilter
 */
@Service
public class JwtService {

    @Value("${app.security.configuration.signature}")
    private String signatureKey;
    @Value("${app.security.configuration.issuer}")
    private String apiIssuer;


    /**
     * creates a SecretKey
     * @return
     */
    private SecretKey encondeKey(){
        return Keys.hmacShaKeyFor(signatureKey.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * Generate a new token
     *
     * @param claims
     * @return string of a jwt token
     */
    public String generateJwtToken(Map<String,Object> claims){
        return Jwts.builder()
                .setIssuer(apiIssuer)
                .setIssuedAt(new Date())
                .setClaims(claims)
                .signWith(this.encondeKey())
                .compact();
    }

    /**
     * validates a token
     *
     * @param token token created and signed by application
     * @return the claims
     */
    public Claims validateJwtToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(this.encondeKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e){
            throw new BadCredentialsException(("Invalid Token "));
        }
    }



}
