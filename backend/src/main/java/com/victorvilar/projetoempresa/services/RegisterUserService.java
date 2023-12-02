package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.ApplicationUserRole;
import com.victorvilar.projetoempresa.dto.applicationuser.ApplicationUserDto;
import com.victorvilar.projetoempresa.domain.ApplicationUser;
import com.victorvilar.projetoempresa.mappers.ApplicationUserMapper;
import com.victorvilar.projetoempresa.repository.ApplicationUserRepository;
import com.victorvilar.projetoempresa.repository.ApplicationUserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegisterUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserRolesRepository applicationUserRolesRepository;
    private final ApplicationUserMapper mapper;

    @Autowired
    RegisterUserService(
            ApplicationUserRepository applicationUserRepository,
            ApplicationUserMapper mapper,
            ApplicationUserRolesRepository applicationUserRolesRepository){

        this.applicationUserRepository = applicationUserRepository;
        this.applicationUserRolesRepository = applicationUserRolesRepository;
        this.mapper = mapper;

    }

    public void register(ApplicationUserDto applicationuserDto) {
        ApplicationUser applicationuser = this.mapper.toApplicationUser(applicationuserDto);
        Set<ApplicationUserRole> roles = (Set<ApplicationUserRole>) applicationuserDto
                .getRoles()
                .stream()
                .map(e -> this.applicationUserRolesRepository.findByRoleName(e))
                .collect(Collectors.toSet());

        applicationuser.setRoles(roles);
        applicationUserRepository.save(applicationuser);

    }
}
