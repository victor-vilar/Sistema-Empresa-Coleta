package com.victorvilar.projetoempresa.dto.contract;

import com.victorvilar.projetoempresa.dto.contract.interfaces.ContractDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ContractResponseDto;
import com.victorvilar.projetoempresa.enums.ContractStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractResponseImplDto implements ContractResponseDto {


    private Long id;
    private String number;
    private String customerId;


    //@JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate beginDate;

    //@JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate endDate;

    private List<ItemContractResponseImplDto> itens = new ArrayList<ItemContractResponseImplDto>();

    //each new contract has an 'ATIVO' status
    private String contractStatus;

    public ContractResponseImplDto() {
    }

    public ContractResponseImplDto(Long id, String number, String customerId, LocalDate beginDate, LocalDate endDate, List<ItemContractResponseImplDto> itens) {
        this.id = id;
        this.number = number;
        this.customerId = customerId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String clientId) {
        this.customerId = clientId;
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

    public List<ItemContractResponseImplDto> getItens() {
        return itens;
    }


    public void setItens(List<ItemContractResponseImplDto> itens) {
        this.itens = itens;
    }

    public static ContractResponseDtoBuilder builder(){
        return new ContractResponseDtoBuilder();
    }

    public void setContractStatus(String contractStatus){
        this.contractStatus = ContractStatus.valueOf(contractStatus).getName();
    }
    public String getContractStatus(){
        return this.contractStatus;
    }


    public static final class ContractResponseDtoBuilder{

        private Long id;
        private String number;
        private String customerId;
        private LocalDate beginDate;
        private LocalDate endDate;
        private List<ItemContractResponseImplDto> itens = new ArrayList<ItemContractResponseImplDto>();
        private String contractStatus;

        private ContractResponseDtoBuilder id(Long id){
            this.id = id;
            return this;
        }

        public ContractResponseDtoBuilder number(String number){
            this.number = number;
            return this;
        }

        public ContractResponseDtoBuilder beginDate(LocalDate beginDate){
            this.beginDate = beginDate;
            return this;
        }

        public ContractResponseDtoBuilder endDate(LocalDate endDate){
            this.endDate = endDate;
            return this;
        }

        public ContractResponseDtoBuilder customer(String customer){
            this.customerId = customer;
            return this;
        }

        public ContractResponseDtoBuilder contractStatus(String contractStatus){
            this.contractStatus = contractStatus;
            return this;
        }

        public ContractResponseImplDto build(){
            ContractResponseImplDto contract = new ContractResponseImplDto();
            contract.setNumber(this.number);
            contract.setBeginDate(this.beginDate);
            contract.setEndDate(this.endDate);
            contract.setCustomerId(this.customerId);
            contract.setContractStatus(this.contractStatus);
            return contract;
        }


    }
}
