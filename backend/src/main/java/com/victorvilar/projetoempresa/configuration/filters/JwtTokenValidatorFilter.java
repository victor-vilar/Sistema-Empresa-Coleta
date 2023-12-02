package com.victorvilar.projetoempresa.configuration.filters;

import com.victorvilar.projetoempresa.repository.ApplicationUserRepository;
import com.victorvilar.projetoempresa.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final String TOKEN_HEADER = "Authorization";
    private ApplicationUserRepository applicationUserRepository;
    private final JwtService jwtService;

    public JwtTokenValidatorFilter(JwtService jwtService){
        this.jwtService =jwtService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(TOKEN_HEADER);

        if(token != null && getBasicAuthenticationType(token)){

            String jwt = this.getAuthenticationTokenCode(token);
            Claims claims = this.jwtService.validateJwtToken(jwt);

            Authentication auth = new UsernamePasswordAuthenticationToken(claims.get("username"), null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("authorities").toString()));

            SecurityContextHolder.getContext().setAuthentication(auth);


        }


        filterChain.doFilter(request,response);
    }

    private String getAuthenticationTokenCode(String token){
        return token.substring(7);
    }

    /**
     * check if the type of authentication equals 'Basic '
     * if it is a basic authentication the filter must not execute
     *
     * @param token
     * @return
     */
    private boolean getBasicAuthenticationType(String token){

        if(!token.substring(0,6).equals("Basic ")){
            return true;
        }

        return false;
    }

    /**
     * this filter doesn't need to be executed if the request path
     * is equals the login endpoint.
     *
     * @param request
     * @return
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return request.getServletPath().equals("/v1/login");
    }
}
