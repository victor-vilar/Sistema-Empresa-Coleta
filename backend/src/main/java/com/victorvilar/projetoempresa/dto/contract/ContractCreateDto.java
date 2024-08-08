package com.victorvilar.projetoempresa.dto.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import com.victorvilar.projetoempresa.dto.contract.interfaces.ContractDto;
import com.victorvilar.projetoempresa.enums.ContractStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractCreateDto implements ContractDto {



    @NotBlank(message ="The contract must have a number")
    private String number;

    @NotNull(message = "The contract must have a start date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate beginDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "the contract must have a end date")
    private LocalDate endDate;

    //@NotEmpty(message = "A contract must have at least one item")
    private List<ItemContractCreateDto> itens = new ArrayList<>();

    @NotNull(message = "the contract must have a customer")
    private String customerId;

    private ContractStatus contractStatus;

    public ContractCreateDto() {
    }

    public ContractCreateDto(String number, LocalDate beginDate, LocalDate endDate, List<ItemContractCreateDto> itens, String customerId) {
        this.number = number;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.itens = itens;
        this.customerId = customerId;

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ItemContractCreateDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemContractCreateDto> itens) {
        this.itens = itens;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setContractStatus(String contractStatus){
        this.contractStatus = ContractStatus.getByName(contractStatus);
    }

    public ContractStatus getContractStatus(){
        return this.contractStatus;
    }

    public static ContractCreateDtoBuilder builder(){
        return new ContractCreateDtoBuilder();
    }

    public static final class ContractCreateDtoBuilder{

        private String number;
        private LocalDate beginDate;
        private LocalDate endDate;
        private List<ItemContractCreateDto> itens = new ArrayList<ItemContractCreateDto>();
        private String customerId;
        private String contractStatus;

        public ContractCreateDtoBuilder number(String number){
            this.number = number;
            return this;
        }

        public ContractCreateDtoBuilder beginDate(LocalDate beginDate){
            this.beginDate = beginDate;
            return this;
        }

        public ContractCreateDtoBuilder endDate(LocalDate endDate){
            this.endDate = endDate;
            return this;
        }

        public ContractCreateDtoBuilder customer(String customer){
            this.customerId = customer;
            return this;
        }
        public ContractCreateDtoBuilder contractStatus(String contractStatus){
            this.contractStatus = contractStatus;
            return this;
        }

        public ContractCreateDto build(){
            ContractCreateDto contract = new ContractCreateDto();
            contract.setNumber(this.number);
            contract.setBeginDate(this.beginDate);
            contract.setEndDate(this.endDate);
            contract.setCustomerId(this.customerId);
            contract.setContractStatus(this.contractStatus);
            return contract;
        }

    }
    
}
