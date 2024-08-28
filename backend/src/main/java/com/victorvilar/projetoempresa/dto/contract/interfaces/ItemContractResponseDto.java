package com.victorvilar.projetoempresa.dto.contract.interfaces;

import java.io.Serializable;

/**
 * ResponseDtos return the string name of your dependencies
 * */
public interface ItemContractResponseDto extends ItemContractDto, Serializable {

    String getResidue();
    String getEquipment();
    String getMeasurementUnit();
}
