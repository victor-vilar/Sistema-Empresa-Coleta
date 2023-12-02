package com.victorvilar.projetoempresa.dto.applicationuser;

import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationUserDto {


    @NotNull(message = "The user must have a username")
    private String username;
    @NotNull(message = "the user must have a password")
    private String password;
    @NotNull(message="the user must have at least one role")
    private Set<String> roles = new HashSet<>();

    private String profilePhotoUrl;

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        roles.forEach(role -> this.roles.add(role));

    }
}
