package com.victorvilar.projetoempresa.dto.contract.interfaces;

import com.victorvilar.projetoempresa.enums.ContractStatus;

import java.time.LocalDate;
import java.util.List;

public interface ContractDto {

    public String getCustomerId();
    public List<? extends ItemContractDto>  getItens();
    public String getNumber();
    public LocalDate getBeginDate();
    public LocalDate getEndDate();

}
