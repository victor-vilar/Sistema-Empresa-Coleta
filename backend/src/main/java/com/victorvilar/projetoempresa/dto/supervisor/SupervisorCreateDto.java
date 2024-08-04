package com.victorvilar.projetoempresa.dto.supervisor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SupervisorCreateDto implements SupervisorDto {


    @NotBlank(message = "The name of supervisor is required")
    private String name;
    private String role;
    @NotBlank(message = "The phonenumer of supervisor is required")
    private String phoneNumber;
    @NotBlank @Email(message = "the email of supervisor is required")
    private String email;

    @NotBlank(message= "The customer of supervisor is required")
    private String customerId;

    public SupervisorCreateDto() {
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String id) {
        this.customerId = id;
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

    public static SupervisorCreateDtoBuilder builder(){
        return new SupervisorCreateDtoBuilder();
    }

    public static class SupervisorCreateDtoBuilder{

        private String name;
        private String role;
        private String phoneNumber;
        private String email;
        private String customerId;

        public SupervisorCreateDtoBuilder name(String name){
            this.name =name;
            return this;
        }

        public SupervisorCreateDtoBuilder role(String role){
            this.role = role;
            return this;
        }

        public SupervisorCreateDtoBuilder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public SupervisorCreateDtoBuilder email(String email){
            this.email = email;
            return this;
        }

        public SupervisorCreateDtoBuilder customerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public SupervisorCreateDto build(){
            SupervisorCreateDto supervisor = new SupervisorCreateDto();
            supervisor.setName(this.name);
            supervisor.setRole(this.role);
            supervisor.setPhoneNumber(this.phoneNumber);
            supervisor.setEmail(this.email);
            supervisor.setCustomerId(this.customerId);
            return supervisor;
        }




    }



}
