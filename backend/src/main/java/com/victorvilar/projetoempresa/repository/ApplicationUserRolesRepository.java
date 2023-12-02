package com.victorvilar.projetoempresa.repository;

import com.victorvilar.projetoempresa.domain.ApplicationUser;
import com.victorvilar.projetoempresa.domain.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationUserRolesRepository extends JpaRepository<ApplicationUserRole,Long> {

    List<ApplicationUserRole> findByApplicationUsers(ApplicationUser user);
    ApplicationUserRole findByRoleName(String role);

}
