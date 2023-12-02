package com.victorvilar.projetoempresa.configuration.authenticationproviders;

import com.victorvilar.projetoempresa.domain.ApplicationUser;
import com.victorvilar.projetoempresa.domain.ApplicationUserRole;
import com.victorvilar.projetoempresa.exceptions.ApplicationUserNotFoundException;
import com.victorvilar.projetoempresa.exceptions.CredentialsNotFoundException;
import com.victorvilar.projetoempresa.repository.ApplicationUserRolesRepository;
import com.victorvilar.projetoempresa.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationUserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private ApplicationUserRolesRepository rolesRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApplicationUser user = this.applicationUserRepository.findByUsername(authentication.getName()).orElseThrow(() ->new ApplicationUserNotFoundException("Application User not found !"));
        String encodedPassword = this.passwordEncoder.encode(authentication.getCredentials().toString());


        //if user it is not null and password matches then return an authentication objects with its roles
        if(user != null && this.passwordEncoder.matches(user.getPassword(),encodedPassword)){
            List<ApplicationUserRole> roles = this.rolesRepository.findByApplicationUsers(user);
            return new UsernamePasswordAuthenticationToken(authentication.getName(),null,roles);
       }

        //if the user it is not null, but its password do not matches then thorw a new exception.
        throw new CredentialsNotFoundException("Application User not found !");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
