package com.victorvilar.projetoempresa.configuration.filters;

import com.victorvilar.projetoempresa.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    private final String TOKEN_HEADER = "Authorization";
    private final JwtService jwtService;

    @Autowired
    public JwtTokenGeneratorFilter(JwtService service){
        this.jwtService =service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


            //check if an authentication is saved
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            //if authentication != null
            if(authentication != null){
                Map<String,Object> claims = new HashMap<>();
                claims.put("username",authentication.getName());
                claims.put("authorities",authentication.getAuthorities().toString());
                String jwt = this.jwtService.generateJwtToken(claims);
                response.setHeader(TOKEN_HEADER,jwt);
            }

            filterChain.doFilter(request,response);
    }


}
