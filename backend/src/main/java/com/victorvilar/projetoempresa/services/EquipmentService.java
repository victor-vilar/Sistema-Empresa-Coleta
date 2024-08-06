package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.dto.equipment.EquipmentCreateDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentResponseDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentUpdateDto;
import com.victorvilar.projetoempresa.domain.Equipment;
import com.victorvilar.projetoempresa.exceptions.EquipmentNotFoundException;
import com.victorvilar.projetoempresa.mappers.EquipmentMapper;
import com.victorvilar.projetoempresa.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentService(
            EquipmentRepository repository,
            EquipmentMapper equipmentMapper){
        this.repository = repository;
        this.equipmentMapper = equipmentMapper;
    }

    public List<EquipmentResponseDto> getAll(){
        return this.equipmentMapper.toEquipmentResponseDtoList(this.repository.findAll());
    }

    public EquipmentResponseDto getById(Long id){
        return this.equipmentMapper.toEquipmentResponseDto(
                this.repository.findById(id)
                        .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found !"))
        );
    }

    public Equipment findById(Long id){
                return this.repository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found !"));
    }

    @Transactional
    public EquipmentResponseDto save(EquipmentCreateDto equipmentCreateDto){
        Equipment equipment = this.equipmentMapper.toEquipment(equipmentCreateDto);
        return this.equipmentMapper.toEquipmentResponseDto(this.repository.save(equipment));
    }

    @Transactional
    public EquipmentResponseDto update(EquipmentUpdateDto equipmentUpdateDto){
        Equipment equipment = this.findById(equipmentUpdateDto.getId());
        equipment.setEquipmentName(equipmentUpdateDto.getEquipmentName());
        equipment.setSizeInMeterCubic(equipmentUpdateDto.getSizeInMeterCubic());
        return this.equipmentMapper.toEquipmentResponseDto(this.repository.save(equipment));

    }

    @Transactional
    public void delete(Long id){
        this.repository.delete(this.findById(id));
    }

    public Integer getEntityCount(){
        return this.repository.getEntityCount();
    }

}
