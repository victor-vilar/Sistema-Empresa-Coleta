package com.victorvilar.projetoempresa.dto.customer;

import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerRequestDto;
import jakarta.validation.constraints.NotBlank;


public class CustomerCreateDto implements CustomerRequestDto {


    @NotBlank(message="The name of the client or company is required")
    private String nameCompanyName;

    @NotBlank(message="The CPF or CNPJ can't be blank")
    private String cpfCnpj;

    private boolean isActive;


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

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }
    public boolean isActive(){
        return this.isActive;
    }

    public static CustomerCreateDtoBuilder builder() {
        return new CustomerCreateDtoBuilder();
    }


    public static class CustomerCreateDtoBuilder{
        private String nameCompanyName;
        private String cpfCnpj;
        private boolean isActive;

        public CustomerCreateDtoBuilder nameCompanyName(String nameCompanyName){
            this.nameCompanyName = nameCompanyName;
            return this;
        }

        public CustomerCreateDtoBuilder cpfCnpj(String cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
            return this;
        }

        public CustomerCreateDtoBuilder isActive(boolean isActive){
            this.isActive = isActive;
            return this;
        }


        public CustomerCreateDto build(){
            CustomerCreateDto dto = new CustomerCreateDto();
            dto.setNameCompanyName(this.nameCompanyName);
            dto.setCpfCnpj(this.cpfCnpj);
            dto.setActive(this.isActive);
            return dto;
        }
    }


}
