package com.victorvilar.projetoempresa.dto.contract.interfaces;

import java.io.Serializable;

public interface ContractResponseDto extends ContractDto, Serializable {

    String getContractStatus();
}
