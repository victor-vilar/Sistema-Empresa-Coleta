package com.victorvilar.projetoempresa.dto.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import com.victorvilar.projetoempresa.enums.ContractStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractUpdateDto {

    @NotNull(message ="A contract to update must have a number")
    private Long id;

    @NotBlank(message ="The contract must have a number")
    private String number;

    @NotNull(message = "The contract must have a start date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate beginDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "the contract must have a end date")
    private LocalDate endDate;

    @NotNull(message = "the contract must have a client")
    private String customerId;

    @NotEmpty(message = "A contract must have at least one item")
    private List<ItemContractUpdateDto> itens  = new ArrayList<>();

    @NotNull
    private ContractStatus contractStatus;


    public List<ItemContractUpdateDto> getItens() {
        return itens;
    }
    public void setItens(List<ItemContractUpdateDto> itens) {
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

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String clientId) {
        this.customerId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static ContractUpdateDtoBuilder builder(){
        return new ContractUpdateDtoBuilder();
    }

    //getters and setters - status
    public void setContractStatus(String contractStatus){
        this.contractStatus = ContractStatus.getByName(contractStatus);
    }
    public ContractStatus getContractStatus(){
        return this.contractStatus;
    }
    //--------------

    public static final class ContractUpdateDtoBuilder{

        private String number;
        private LocalDate beginDate;
        private LocalDate endDate;
        private List<ItemContractUpdateDto> itens = new ArrayList<ItemContractUpdateDto>();
        private String customerId;
        private String contractStatus;

        public ContractUpdateDtoBuilder number(String number){
            this.number = number;
            return this;
        }

        public ContractUpdateDtoBuilder beginDate(LocalDate beginDate){
            this.beginDate = beginDate;
            return this;
        }

        public ContractUpdateDtoBuilder endDate(LocalDate endDate){
            this.endDate = endDate;
            return this;
        }

        public ContractUpdateDtoBuilder customer(String customer){
            this.customerId = customer;
            return this;
        }

        public ContractUpdateDtoBuilder contractStatus(String contractStatus){
            this.contractStatus = contractStatus;
            return this;
        }

        public ContractUpdateDto build(){
            ContractUpdateDto contract = new ContractUpdateDto();
            contract.setNumber(this.number);
            contract.setBeginDate(this.beginDate);
            contract.setEndDate(this.endDate);
            contract.setCustomerId(this.customerId);
            contract.setContractStatus(this.contractStatus);
            return contract;
        }

    }
    
    
}
