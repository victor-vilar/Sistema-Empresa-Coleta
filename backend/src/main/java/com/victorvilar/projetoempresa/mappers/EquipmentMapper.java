package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.dto.equipment.EquipmentCreateDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentResponseDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentUpdateDto;
import com.victorvilar.projetoempresa.domain.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentMapper {

    private final ModelMapper mapper;

    @Autowired
    public EquipmentMapper(ModelMapper map){
        this.mapper = map;
    }

    public Equipment toEquipament(EquipmentCreateDto equipmentCreateDto){
        return this.mapper.map(equipmentCreateDto,Equipment.class);
    }

    public Equipment toEquipment(EquipmentUpdateDto equipmentUpdateDto){
        return this.mapper.map(equipmentUpdateDto,Equipment.class);
    }

    public EquipmentResponseDto toEquipmentResponseDto(Equipment equipment){
        return this.mapper.map(equipment, EquipmentResponseDto.class);
    }

    public List<EquipmentResponseDto> toEquipmentResponseDtoList(List<Equipment> list){
        return list.stream().map(e -> this.toEquipmentResponseDto(e)).collect(Collectors.toList());
    }
}
