package com.victorvilar.projetoempresa.dto.contract.interfaces;

import com.victorvilar.projetoempresa.enums.ContractStatus;

public interface ContractRequestDto extends ContractDto{

    public ContractStatus getContractStatus();
}
