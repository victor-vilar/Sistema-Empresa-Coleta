package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Equipment;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentResponseDto;
import com.victorvilar.projetoempresa.dto.equipment.interfaces.EquipmentDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentCreateDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EquipmentMapperTest {

    @Autowired
    private EquipmentMapper mapper;

    Equipment equipment1;
    Equipment equipment2;
    EquipmentCreateDto equipmentCreateDto;
    EquipmentUpdateDto equipmentUpdateDto;

    @BeforeEach
    void setUp(){
        equipment1 = new Equipment(1L,"Caçamba 5m³",5);
        equipment2 = new Equipment(2L,"Caçamba 15m³",15);
        equipmentCreateDto = new EquipmentCreateDto("Caçamba 20m³",20);
        equipmentUpdateDto = new EquipmentUpdateDto(1L,"Caçamba 30m³",30);


    }

    @Test
    void toEquipment_WhenPassingCreateDto() {
        Equipment equipment = mapper.toEquipment(equipmentCreateDto);
        compare(equipmentCreateDto,equipment);
    }

    @Test
    void toEquipment_WhenPassingUpdateDto() {
        Equipment equipment = mapper.toEquipment(equipmentUpdateDto);
        assertEquals(equipmentUpdateDto.getId(), equipment.getId());
        compare(equipmentUpdateDto,equipment);
    }

    @Test
    void toEquipmentResponseDto() {
        EquipmentResponseDto dto1 = mapper.toEquipmentResponseDto(equipment1);
        EquipmentResponseDto dto2 = mapper.toEquipmentResponseDto(equipment2);
        compare(dto1,equipment1);
        compare(dto2,equipment2);
    }

    @Test
    void toEquipmentResponseDtoList() {
        List<EquipmentResponseDto> dtos = mapper.toEquipmentResponseDtoList(List.of(equipment1,equipment2));
        List<Equipment> equipments = List.of(equipment1,equipment2);
        for(int i = 0; i < dtos.size() ; i++){
            assertEquals(dtos.get(i).getId(),equipments.get(i).getId());
            compare(dtos.get(i),equipments.get(i));
        }
    }

    void compare(EquipmentDto dto, Equipment equipment){
        assertEquals(dto.getEquipmentName(),equipment.getEquipmentName());
        assertEquals(dto.getSizeInMeterCubic(),equipment.getSizeInMeterCubic());
    }
}