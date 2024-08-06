package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.Equipment;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentCreateDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentResponseDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentUpdateDto;
import com.victorvilar.projetoempresa.exceptions.EquipmentNotFoundException;
import com.victorvilar.projetoempresa.exceptions.ResidueNotFoundException;
import com.victorvilar.projetoempresa.repository.EquipmentRepository;
import com.victorvilar.projetoempresa.mappers.EquipmentMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceTest {

    @Spy
    @InjectMocks
    private EquipmentService service;

    @Mock
    private EquipmentMapper mapper;

    @Mock
    private EquipmentRepository repository;

    Equipment equipment1;
    Equipment equipment2;
    EquipmentCreateDto createDto;
    EquipmentUpdateDto updateDto;
    EquipmentResponseDto responseDto1;
    EquipmentResponseDto responseDto2;


    @BeforeEach
    void setUp() {
        equipment1 = new Equipment(1L,"container 1000 M",1000);
        equipment2 = new Equipment(2L,"container 5000 M",5000);
        createDto = new EquipmentCreateDto("container 1000 M",1000);
        updateDto = new EquipmentUpdateDto(1L,"container 1000 M",1000);
        responseDto1 = new EquipmentResponseDto(1L,"container 1000 M",1000);
        responseDto2 = new EquipmentResponseDto(2L,"container 5000 M",5000);
    }

    @Test
    void getAll_WhenSuccessfull() {

        Mockito.when(repository.findAll()).thenReturn(List.of(equipment1,equipment2));
        Mockito.when(mapper.toEquipmentResponseDtoList(List.of(equipment1,equipment2))).thenReturn(List.of(responseDto1,responseDto2));
        List<EquipmentResponseDto> equipmentResponseList = service.getAll();
        assertFalse(equipmentResponseList.isEmpty());
        assertEquals(equipmentResponseList.size(),2);
        assertEquals(equipmentResponseList.get(0).getId(),responseDto1.getId());
        assertEquals(equipmentResponseList.get(1).getId(),responseDto2.getId());
        assertEquals(equipmentResponseList.get(0).getEquipmentName(),responseDto1.getEquipmentName());
        assertEquals(equipmentResponseList.get(1).getEquipmentName(),responseDto2.getEquipmentName());
        assertEquals(equipmentResponseList.get(0).getSizeInMeterCubic(),responseDto1.getSizeInMeterCubic());
        assertEquals(equipmentResponseList.get(1).getSizeInMeterCubic(),responseDto2.getSizeInMeterCubic());
        verify(repository,times(1)).findAll();
    }

    @Test
    void getById_WhenSuccessfull() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(equipment1));
        Mockito.when(mapper.toEquipmentResponseDto(equipment1)).thenReturn(responseDto1);
        EquipmentResponseDto equipmentResponse = service.getById(1L);
        assertNotNull(equipmentResponse);
        assertEquals(equipmentResponse.getId(),responseDto1.getId());
        assertEquals(equipmentResponse.getEquipmentName(),responseDto1.getEquipmentName());
        assertEquals(equipmentResponse.getSizeInMeterCubic(),responseDto1.getSizeInMeterCubic());
        verify(repository,times(1)).findById(1L);

    }

    @Test
    void getById_WhenPassInvalidId_ThrowsEquipmentNotFoundException() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        EquipmentNotFoundException exception = Assertions.assertThrows(EquipmentNotFoundException.class, () -> this.service.getById(1L));
        assertEquals("Equipment not found !",exception.getMessage());
        assertEquals(exception.getClass(),EquipmentNotFoundException.class);
    }

    @Test
    void findById_WhenSuccessfull() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(equipment1));
        Equipment equipment = service.findById(1L);
        assertNotNull(equipment);
        assertEquals(equipment.getId(),equipment1.getId());
        assertEquals(equipment.getEquipmentName(),equipment1.getEquipmentName());
        assertEquals(equipment.getSizeInMeterCubic(),equipment1.getSizeInMeterCubic());
        verify(repository,times(1)).findById(1L);
    }

    @Test
    void findById_WhenPassInvalidId_ThrowsEquipmentNotFoundException() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        EquipmentNotFoundException exception = Assertions.assertThrows(EquipmentNotFoundException.class, () -> this.service.findById(1L));
        assertEquals("Equipment not found !",exception.getMessage());
        assertEquals(exception.getClass(),EquipmentNotFoundException.class);
    }

    @Test
    void save_WhenSuccessfull() {
        Mockito.when(mapper.toEquipment(createDto)).thenReturn(equipment1);
        Mockito.when(repository.save(equipment1)).thenReturn(equipment1);
        Mockito.when(mapper.toEquipmentResponseDto(equipment1)).thenReturn(responseDto1);
        EquipmentResponseDto equipmentResponse = service.save(createDto);
        assertNotNull(equipmentResponse);
        assertEquals(equipmentResponse.getId(),responseDto1.getId());
        assertEquals(equipmentResponse.getEquipmentName(),responseDto1.getEquipmentName());
        assertEquals(equipmentResponse.getSizeInMeterCubic(),responseDto1.getSizeInMeterCubic());
        verify(repository,times(1)).save(equipment1);
    }

    @Test
    void update_WhenSuccessfull() {
        doReturn(equipment1).when(service).findById(1L);
        Mockito.when(repository.save(equipment1)).thenReturn(equipment1);
        Mockito.when(mapper.toEquipmentResponseDto(equipment1)).thenReturn(responseDto1);
        EquipmentResponseDto equipmentResponse = service.update(updateDto);
        assertNotNull(equipmentResponse);
        assertEquals(equipmentResponse.getId(),responseDto1.getId());
        assertEquals(equipmentResponse.getEquipmentName(),responseDto1.getEquipmentName());
        assertEquals(equipmentResponse.getSizeInMeterCubic(),responseDto1.getSizeInMeterCubic());
        verify(repository,times(1)).save(equipment1);

    }


    @Test
    void delete() {
        doReturn(equipment1).when(service).findById(1L);
        service.delete(1L);
        verify(repository,times(1)).delete(equipment1);
    }

    @Test
    void getEntityCount_WhenSuccessfull() {

        when(repository.getEntityCount()).thenReturn(2);
        int qtd = this.service.getEntityCount();
        assertEquals(2,qtd);
        verify(repository,times(1)).getEntityCount();
    }
}