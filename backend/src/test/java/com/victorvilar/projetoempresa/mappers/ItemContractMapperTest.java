package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.CollectionFrequency;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemContractMapperTest {

    @Test
    void toItemContract() {
    }

    @Test
    void toItemContractResponseDto() {
    }

    @Test
    void toItemContractList() {
    }

    void compare(ItemContractDto dto, ItemContract item){
        assertEquals(dto.getEquipmentQuantity(), item.getEquipmentQuantity());
        assertEquals(dto.getItemValue(), item.getItemValue());
        assertEquals(dto.getQtdOfResidue(), item.getQtdOfResidue());
        compare(dto.getCollectionFrequency(),item.getCollectionFrequency());
    }

    void compare(CollectionFrequency collection1, CollectionFrequency collection2){
        assertEquals(collection1.getDays().size(), collection2.getDays().size());
        assertEquals(collection1.getSchedule(),collection2.getSchedule());
    }

}