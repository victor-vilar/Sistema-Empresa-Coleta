package com.victorvilar.projetoempresa.domain;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@Table(name="roles")
public class ApplicationUserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToMany(mappedBy = "applicationUserRoles")
    private Set<ApplicationUser> applicationUsers = new HashSet<>();


    public ApplicationUserRole() {
    }

    public ApplicationUserRole(Long id, String roleName, Set<ApplicationUser> applicationUserList) {
        this.id = id;
        this.roleName = roleName;
        this.applicationUsers = applicationUserList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public Set<ApplicationUser> getApplicationUserList() {
        return applicationUsers;
    }

    public void setApplicationUserList(Set<ApplicationUser> applicationUserList) {
        this.applicationUsers = applicationUserList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUserRole that = (ApplicationUserRole) o;
        return Objects.equals(id, that.id) && Objects.equals(roleName, that.roleName) && Objects.equals(applicationUsers, that.applicationUsers);
    }

    @Override
    public String toString(){
        return this.getAuthority();
    }



}
