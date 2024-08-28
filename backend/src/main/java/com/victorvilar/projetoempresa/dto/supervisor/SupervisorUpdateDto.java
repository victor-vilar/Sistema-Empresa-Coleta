package com.victorvilar.projetoempresa.dto.supervisor;

import com.victorvilar.projetoempresa.dto.supervisor.interfaces.SupervisorDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SupervisorUpdateDto implements SupervisorDto {

    @NotNull(message="A supervisor to update needs to have a id number")
    private Long id;

    @NotBlank(message = "The name of supervisor is required")
    private String name;
    private String role;

    @NotBlank(message = "The phonenumer of supervisor is required")
    private String phoneNumber;
    @NotBlank @Email(message = "the email of supervisor is required")
    private String email;

    @NotBlank(message= "The customer of supervisor is required")
    private String customerId;

    public SupervisorUpdateDto() {
    }

    public SupervisorUpdateDto(Long id, String name, String role, String phoneNumber, String email, String customerId) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public static SupervisorUpdateDtoBuilder builder(){
        return new SupervisorUpdateDtoBuilder();
    }

    public static class SupervisorUpdateDtoBuilder{

        private Long id;
        private String name;
        private String role;
        private String phoneNumber;
        private String email;
        private String customerId;

        public SupervisorUpdateDtoBuilder id(Long id){
            this.id = id;
            return this;
        }

        public SupervisorUpdateDtoBuilder name(String name){
            this.name =name;
            return this;
        }

        public SupervisorUpdateDtoBuilder role(String role){
            this.role = role;
            return this;
        }

        public SupervisorUpdateDtoBuilder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public SupervisorUpdateDtoBuilder email(String email){
            this.email = email;
            return this;
        }

        public SupervisorUpdateDtoBuilder customerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public SupervisorUpdateDto build(){
            SupervisorUpdateDto supervisor = new SupervisorUpdateDto();
            supervisor.setId(this.id);
            supervisor.setName(this.name);
            supervisor.setRole(this.role);
            supervisor.setPhoneNumber(this.phoneNumber);
            supervisor.setEmail(this.email);
            supervisor.setCustomerId(this.customerId);
            return supervisor;
        }



    }


}
