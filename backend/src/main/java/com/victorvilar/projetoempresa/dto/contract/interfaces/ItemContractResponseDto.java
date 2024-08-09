package com.victorvilar.projetoempresa.dto.contract.interfaces;

/**
 * ResponseDtos return the string name of your dependencies
 * */
public interface ItemContractResponseDto extends ItemContractDto {

    String getResidue();
    String getEquipment();
    String getMeasurementUnit();
}
