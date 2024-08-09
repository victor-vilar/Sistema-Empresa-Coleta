package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.CollectionFrequency;
import com.victorvilar.projetoempresa.domain.Equipment;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.domain.Residue;
import com.victorvilar.projetoempresa.dto.contract.ItemContractCreateDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractUpdateDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractRequestDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractResponseDto;
import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import com.victorvilar.projetoempresa.enums.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemContractMapperTest {

    @Autowired
    private ItemContractMapper mapper;

    ItemContract itemContract1;
    ItemContract itemContract2;
    ItemContractCreateDto itemContractCreateDto;
    ItemContractUpdateDto itemContractUpdateDto;
    Residue residue;
    Equipment equipment;
    CollectionFrequency collectionFrequency;
    @BeforeEach
    public void setUp(){

        residue = new Residue(1L,"Extraordinário","Resíduo extraordinário");
        equipment = new Equipment(1L,"Caçamba 5m", 5.00);

        collectionFrequency = new CollectionFrequency();
        collectionFrequency.setId(1L);
        collectionFrequency.setSchedule("MENSAL");
        collectionFrequency.setDays(Set.of(Weekday.SEGUNDA,Weekday.QUARTA));

        itemContract1 = new ItemContract(residue,equipment,100.00, BigDecimal.valueOf(100.00),"Coleta de Resíduo Extraordinário",10,collectionFrequency, MeasurementUnit.LITROS);
        itemContract2 = new ItemContract(residue,equipment,200.00, BigDecimal.valueOf(200.00),"Coleta de Resíduo Extraordinário",20,collectionFrequency, MeasurementUnit.LITROS);
        itemContractCreateDto = new ItemContractCreateDto(1L,1L,100.00, BigDecimal.valueOf(100.00),"Coleta de Resíduo Extraordinário",10,collectionFrequency, MeasurementUnit.LITROS);
        itemContractUpdateDto = new ItemContractUpdateDto(1L,1L,1L,100.00, BigDecimal.valueOf(100.00),"Coleta de Resíduo Extraordinário",10,collectionFrequency, MeasurementUnit.LITROS);

    }

    @Test
    void toItemContract_WhenPassingCreateDto() {
        ItemContract item = this.mapper.toItemContract(itemContractCreateDto);
        compareRequestDto(itemContractCreateDto,item);

    }

    @Test
    void toItemContract_WhenPassingUpdateDto() {
        ItemContract item = this.mapper.toItemContract(itemContractUpdateDto);
        compareRequestDto(itemContractUpdateDto,item);
    }


    @Test
    void toItemContractResponseDto() {
        ItemContractResponseDto dto = this.mapper.toItemContractResponseDto(itemContract1);
        compareResponseDto(dto,itemContract1);
    }

    @Test
    void toItemContractResponseDtoList() {
        List<ItemContractResponseDto> dtos = this.mapper.toItemContractResponseDto(List.of(itemContract1,itemContract2));
        List<ItemContract> itens = List.of(itemContract1,itemContract2);
        for(int i = 0; i< dtos.size(); i++){
            compareResponseDto(dtos.get(i),itens.get(i));
        }

    }

    void compareRequestDto(ItemContractRequestDto dto, ItemContract item){
        assertEquals(dto.getEquipment(),item.getEquipment().getId());
        assertEquals(dto.getResidue(),item.getResidue().getId());
        assertEquals(dto.getMeasurementUnit(),item.getMeasurementUnit());
        compare(dto,item);
    }

    void compareResponseDto(ItemContractResponseDto dto, ItemContract item){
        assertEquals(dto.getEquipment(),item.getEquipment().getEquipmentName());
        assertEquals(dto.getResidue(),item.getResidue().getType());
        assertEquals(dto.getMeasurementUnit(),item.getMeasurementUnit().getName());
        compare(dto,item);
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