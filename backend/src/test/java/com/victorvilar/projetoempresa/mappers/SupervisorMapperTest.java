package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Supervisor;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorCreateDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorResponseDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorUpdateDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupervisorMapperTest {

    @Autowired
    private SupervisorMapper mapper;

    Supervisor supervisor1;
    Supervisor supervisor2;
    SupervisorCreateDto supervisorCreateDto;
    SupervisorUpdateDto supervisorUpdateDto;

    @BeforeEach
    void setUp(){

        supervisor1 = Supervisor.builder()
                .name("Tobias")
                .role("Administrador")
                .phoneNumber("11111")
                .email("tobias@gmail.com")
                .build();

        supervisor2 = Supervisor.builder()
                .name("Tiririca")
                .role("Quem é o cantor")
                .phoneNumber("22222")
                .email("tiririca@gmail.com")
                .build();

        supervisorCreateDto = SupervisorCreateDto.builder()
                .name("Toninho")
                .role("Cinderelo")
                .phoneNumber("3333")
                .email("toninho@gmail.com")
                .build();

        supervisorUpdateDto = SupervisorUpdateDto.builder()
                .id(1L)
                .name("Cebolinha")
                .role("Plofesso")
                .phoneNumber("4444")
                .email("cebolinha@gmail.com")
                .build();

    }

    @Test
    void toSupervisor_WhenPassingCreateDto() {
        Supervisor supervisor = mapper.toSupervisor(supervisorCreateDto);
        assertNull(supervisor.getId());
        compare(supervisorCreateDto,supervisor);
    }

    @Test
    void toSupervisor_WhenPassingUpdateDto() {
        Supervisor supervisor = mapper.toSupervisor(supervisorUpdateDto);
        assertEquals(supervisorUpdateDto.getId(),supervisor.getId());
        compare(supervisorUpdateDto,supervisor);
    }

    @Test
    void toSupervisorResponseDto() {
        SupervisorResponseDto dto1 = mapper.toSupervisorResponseDto(supervisor1);
        SupervisorResponseDto dto2 = mapper.toSupervisorResponseDto(supervisor2);
        assertEquals(dto1.getId(),supervisor1.getId());
        assertEquals(dto2.getId(),supervisor2.getId());
        compare(dto1,supervisor1);
        compare(dto2,supervisor2);
    }

    @Test
    void toSupervisorResponseDtoList() {
        List<SupervisorResponseDto> dtos = mapper.toSupervisorResponseDtoList(List.of(supervisor1,supervisor2));
        List<Supervisor> supervisors = List.of(supervisor1,supervisor2);
        for(int i = 0; i < dtos.size(); i++){
            assertEquals(dtos.get(i).getId(),supervisors.get(i).getId());
            compare(dtos.get(i),supervisors.get(i));
        }

    }

    void compare(SupervisorDto dto, Supervisor supervisor){
        assertEquals(dto.getName(),supervisor.getName());
        assertEquals(dto.getRole(),supervisor.getRole());
        assertEquals(dto.getPhoneNumber(),supervisor.getPhoneNumber());
        assertEquals(dto.getEmail(),supervisor.getEmail());
    }

}