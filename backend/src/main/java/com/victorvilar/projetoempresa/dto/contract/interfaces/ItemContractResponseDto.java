package com.victorvilar.projetoempresa.dto.contract.interfaces;

import com.victorvilar.projetoempresa.dto.equipment.EquipmentResponseDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueResponseDto;

import java.io.Serializable;

/**
 * ResponseDtos return the string name of your dependencies
 * */
public interface ItemContractResponseDto extends ItemContractDto, Serializable {

    ResidueResponseDto getResidue();
    EquipmentResponseDto getEquipment();
    String getMeasurementUnit();
}
