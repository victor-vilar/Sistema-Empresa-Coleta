package com.victorvilar.projetoempresa.dto.contract.interfaces;

import com.victorvilar.projetoempresa.enums.MeasurementUnit;

/**
 * RequestDtos search for its dependencies by id
 * */
public interface ItemContractRequestDto extends ItemContractDto{

    Long getResidue();
    Long getEquipment();
    MeasurementUnit getMeasurementUnit();
}
