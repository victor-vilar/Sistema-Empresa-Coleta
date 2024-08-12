package com.victorvilar.projetoempresa.dto.customer;

import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerResponseDto;

public class CustomerResponseSimpleDto implements CustomerResponseDto {

    private String nameCompanyName;
    private String cpfCnpj;
    private boolean isActive;

    public CustomerResponseSimpleDto() {

    }

    public CustomerResponseSimpleDto(String nameCompanyName, String cpfCnpj, boolean isActive) {
        this.nameCompanyName = nameCompanyName;
        this.cpfCnpj = cpfCnpj;
        this.isActive = isActive;
    }

    @Override
    public String getCpfCnpj() {
        return this.cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj){
        this.cpfCnpj = cpfCnpj;
    }

    @Override
    public String getNameCompanyName() {
        return this.nameCompanyName;
    }

    public void setNameCompanyName(String nameCompanyName){
        this.nameCompanyName = nameCompanyName;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }
}
