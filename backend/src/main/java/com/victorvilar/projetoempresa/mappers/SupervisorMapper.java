package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.dto.supervisor.interfaces.SupervisorDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorResponseDto;
import com.victorvilar.projetoempresa.domain.Supervisor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupervisorMapper {

    private final ModelMapper mapper;

    @Autowired
    public SupervisorMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public Supervisor toSupervisor(SupervisorDto supervisorDto){
        return this.mapper.map(supervisorDto, Supervisor.class);
    }

    public SupervisorResponseDto toSupervisorResponseDto(Supervisor supervisor){
        return this.mapper.map(supervisor,SupervisorResponseDto.class);
    }


    public List<SupervisorResponseDto> toSupervisorResponseDtoList(List<Supervisor> supervisors){
        return supervisors.stream().map(e -> toSupervisorResponseDto(e)).collect(Collectors.toList());
    }

}
