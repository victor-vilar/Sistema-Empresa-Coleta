package com.victorvilar.projetoempresa.dto.customer;

import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created to not expose the models of the API
 */
public class CustomerResponseDefaultImplDto implements CustomerResponseDto {


    private String nameCompanyName;
    private String cpfCnpj;
    private boolean isActive;
    private List<SupervisorResponseDto> supervisors = new ArrayList<SupervisorResponseDto>();
    private List<ContractResponseImplDto> contracts = new ArrayList<ContractResponseImplDto>();
    private List<AddressResponseDto> addresses = new ArrayList<AddressResponseDto>();
    private List<ServiceOrderResponseDto> serviceOrders = new ArrayList<>();

    public CustomerResponseDefaultImplDto(){

    }

    public CustomerResponseDefaultImplDto(String cpfCnpj, String nameCompanyName, boolean isActive){
        this.cpfCnpj = cpfCnpj;
        this.nameCompanyName = nameCompanyName;
        this.isActive = isActive;
    }


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

    public void setActive(Boolean active){
        this.isActive = active;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public List<SupervisorResponseDto> getSupervisors(){
        return this.supervisors;
    }

    public void setSupervisors(List<SupervisorResponseDto> supervisors){
        this.supervisors = supervisors;
    }

    public List<ContractResponseImplDto> getContracts() {
        return this.contracts;
    }

    public void setContracts(List<ContractResponseImplDto> contratos) {
        this.contracts = contratos;
    }

    public List<AddressResponseDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponseDto> address) {
        this.addresses = address;
    }

    public List<ServiceOrderResponseDto> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrderResponseDto> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

}
