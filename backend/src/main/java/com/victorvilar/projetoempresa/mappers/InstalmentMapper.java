package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.bill.Instalment;
import com.victorvilar.projetoempresa.dto.bill.InstalmentCreateDto;
import com.victorvilar.projetoempresa.dto.bill.InstalmentUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InstalmentMapper {

    private final ModelMapper mapper;

    public InstalmentMapper(ModelMapper modelMapper){
      this.mapper=modelMapper;
    }

    public Instalment toInstalment(InstalmentCreateDto createDto){
        return this.mapper.map(createDto,Instalment.class);
    }

    public Instalment toInstalment(InstalmentUpdateDto updateDto){
        return this.mapper.map(updateDto,Instalment.class);
    }
}



