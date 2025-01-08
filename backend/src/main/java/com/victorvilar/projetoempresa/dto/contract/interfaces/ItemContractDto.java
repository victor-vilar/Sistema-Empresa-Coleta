package com.victorvilar.projetoempresa.dto.contract.interfaces;

import com.victorvilar.projetoempresa.domain.CollectionFrequency;
import com.victorvilar.projetoempresa.dto.contract.CollectionFrequencyDto;

import java.math.BigDecimal;

public interface ItemContractDto {


    Integer getEquipmentQuantity();
    Double getQtdOfResidue();
    BigDecimal getItemValue();
    CollectionFrequencyDto getCollectionFrequency();




}
