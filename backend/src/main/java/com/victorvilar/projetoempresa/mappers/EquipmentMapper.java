package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.dto.equipment.interfaces.EquipmentDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentResponseDto;
import com.victorvilar.projetoempresa.domain.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipmentMapper {

    private final ModelMapper mapper;

    @Autowired
    public EquipmentMapper(ModelMapper map){
        this.mapper = map;
    }

    public Equipment toEquipment(EquipmentDto equipmentDto){
        return this.mapper.map(equipmentDto,Equipment.class);
    }

    public EquipmentResponseDto toEquipmentResponseDto(Equipment equipment){
        return this.mapper.map(equipment, EquipmentResponseDto.class);
    }

    public List<EquipmentResponseDto> toEquipmentResponseDtoList(List<Equipment> list){
        return list.stream().map(this::toEquipmentResponseDto).toList();
    }
}
