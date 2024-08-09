package com.victorvilar.projetoempresa.dto.contract.interfaces;

import com.victorvilar.projetoempresa.domain.CollectionFrequency;

import java.math.BigDecimal;

public interface ItemContractDto {


    Integer getEquipmentQuantity();
    Double getQtdOfResidue();
    BigDecimal getItemValue();
    CollectionFrequency getCollectionFrequency();




}
