package com.victorvilar.projetoempresa.dto.applicationuser;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationUserResponseDto {


    private String username;
    private Set<String> roles = new HashSet<>();
    private String profilePhotoUrl;

    public ApplicationUserResponseDto(){

    }

    public String getProfilePhotoUrl(){
        return this.profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String url){
        this.profilePhotoUrl = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public static ApplicationUserResponseDtoBuilder builder(){
        return new ApplicationUserResponseDtoBuilder();
    }

    public static class ApplicationUserResponseDtoBuilder{

        private String username;
        private Set<String> roles = new HashSet<>();
        private String perfilPhotoUrl;

        public ApplicationUserResponseDtoBuilder username(String username){
            this.username =username;
            return this;
        }

        public ApplicationUserResponseDtoBuilder roles(Collection<? extends GrantedAuthority> roles){
            this.roles = (Set<String>)roles.stream().map(role -> role.getAuthority()).collect(Collectors.toSet());
            return this;
        }

        public ApplicationUserResponseDtoBuilder perfilPhotoUrl(String url){
            this.perfilPhotoUrl = url;
            return this;
        }

        public ApplicationUserResponseDto build(){
            ApplicationUserResponseDto responseDto = new ApplicationUserResponseDto();
            responseDto.setUsername(this.username);
            responseDto.setRoles(this.roles);
            responseDto.setProfilePhotoUrl(this.perfilPhotoUrl);
            return responseDto;
        }

    }
}
