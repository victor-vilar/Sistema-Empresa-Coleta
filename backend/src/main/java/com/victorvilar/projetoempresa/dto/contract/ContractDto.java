package com.victorvilar.projetoempresa.dto.contract;

import com.victorvilar.projetoempresa.enums.ContractStatus;

import java.time.LocalDate;
import java.util.List;

public interface ContractDto {

    public String getCustomerId();
    public void setCustomerId(String customerId);
    public List<? extends ItemContractDto>  getItens();
    public void setItens(List<? extends ItemContractDto> itens);
    public String getNumber();
    public void setNumber(String number);
    public LocalDate getBeginDate();
    public void setBeginDate(LocalDate beginDate);
    public LocalDate getEndDate();
    public void setEndDate(LocalDate endDate);
    public void setContractStatus(String contractStatus);
    public ContractStatus getContractStatus();
}
