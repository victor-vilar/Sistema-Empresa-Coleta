package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.ApplicationUser;
import com.victorvilar.projetoempresa.dto.applicationuser.ApplicationUserResponseDto;
import com.victorvilar.projetoempresa.mappers.ApplicationUserMapper;
import com.victorvilar.projetoempresa.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final ApplicationUserMapper mapper;
    private final ApplicationUserRepository userRepository;

    @Autowired
    public LoginService(
            ApplicationUserMapper mapper,
            ApplicationUserRepository userRepository
    ){
        this.mapper=mapper;
        this.userRepository= userRepository;
    }

    public ApplicationUserResponseDto getLoggedUserDetails(Authentication authentication) throws UsernameNotFoundException{

        //get userApplication from database
        ApplicationUser user = this.userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found !"));

        //convert to Dto
        ApplicationUserResponseDto applicationUserResponseDto = this.mapper.toApplicationUserResponseDto(user);

        //return
        return applicationUserResponseDto;
    }

}
