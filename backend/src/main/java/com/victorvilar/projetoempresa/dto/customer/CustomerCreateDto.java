package com.victorvilar.projetoempresa.dto.customer;

import jakarta.validation.constraints.NotBlank;


public class CustomerCreateDto {


    @NotBlank(message="The name of the client or company is required")
    private String nameCompanyName;

    @NotBlank(message="The CPF or CNPJ can't be blank")
    private String cpfCnpj;

    public String getNameCompanyName() {
        return nameCompanyName;
    }

    public void setNameCompanyName(String nameCompanyName) {
        this.nameCompanyName = nameCompanyName;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public static CustomerCreateDtoBuilder builder() {
        return new CustomerCreateDtoBuilder();
    }


    public static class CustomerCreateDtoBuilder{
        private String nameCompanyName;
        private String cpfCnpj;

        public CustomerCreateDtoBuilder nameCompanyName(String nameCompanyName){
            this.nameCompanyName = nameCompanyName;
            return this;
        }

        public CustomerCreateDtoBuilder cpfCnpj(String cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
            return this;
        }

        public CustomerCreateDto build(){
            CustomerCreateDto dto = new CustomerCreateDto();
            dto.setNameCompanyName(this.nameCompanyName);
            dto.setCpfCnpj(this.cpfCnpj);
            return dto;
        }
    }


}
