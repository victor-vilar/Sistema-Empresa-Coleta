package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Instalment;
import com.victorvilar.projetoempresa.dto.bill.interfaces.InstalmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstalmentMapper {

    private final ModelMapper mapper;

    public InstalmentMapper(ModelMapper modelMapper){
      this.mapper=modelMapper;
    }

    public Instalment toInstalmentList(InstalmentDto createDto){
        return this.mapper.map(createDto,Instalment.class);
    }

    public List<Instalment> toInstalmentList(List<? extends InstalmentDto> list){
        return list.stream().map(this::toInstalmentList).toList();
    }
}



