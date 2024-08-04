package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.dto.residuetype.ResidueCreateDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueResponseDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueUpdateDto;
import com.victorvilar.projetoempresa.domain.Residue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResidueMapper {


    private final ModelMapper mapper;

    @Autowired
    public ResidueMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public ResidueResponseDto toResidueResponseDto(Residue residue){
        return this.mapper.map(residue, ResidueResponseDto.class);
    }

    public List<ResidueResponseDto> toResidueResponseDtoList(List<Residue> list){
        return list.stream().map(this::toResidueResponseDto).toList();
    }

    public Residue toResidue(ResidueDto residueDto) {
        return this.mapper.map(residueDto, Residue.class);
    }

}
