package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.dto.residuetype.ResidueCreateDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueResponseDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueUpdateDto;
import com.victorvilar.projetoempresa.domain.Residue;
import com.victorvilar.projetoempresa.exceptions.ResidueNotFoundException;
import com.victorvilar.projetoempresa.mappers.ResidueMapper;
import com.victorvilar.projetoempresa.repository.ResidueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class ResidueService {

    private final ResidueRepository repository;
    private final ResidueMapper mapper;

    @Autowired
    public ResidueService(
            ResidueRepository repository,
            ResidueMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ResidueResponseDto> getAll() {
        return this.mapper.toResidueResponseDtoList(this.repository.findAll());
    }


    public ResidueResponseDto getById(Long id){
        return this.mapper.toResidueResponseDto(
                this.repository.findById(id)
                        .orElseThrow(() -> new ResidueNotFoundException("Residue Not Found !"))
        );
    }

    public Residue findById(Long id){
        return this.repository
                .findById(id)
                .orElseThrow(() -> new ResidueNotFoundException("Residue Not Found !"));
    }

    @Transactional
    public ResidueResponseDto save(ResidueCreateDto residueCreateDto){
        Residue residue = this.mapper.toResidue(residueCreateDto);
        return this.mapper.toResidueResponseDto(this.repository.save(residue));
    }

    @Transactional
    public void delete(Long id){
        Residue residue = this.findById(id);
        this.repository.delete(residue);
    }

    @Transactional
    public ResidueResponseDto update(ResidueUpdateDto residueUpdateDto){
        Residue residue = this.findById(residueUpdateDto.getId());
        residue.setType(residueUpdateDto.getType());
        residue.setDescription(residueUpdateDto.getDescription());
        return this.mapper.toResidueResponseDto(this.repository.save(residue));

    }

    public Integer getEntityCount(){
        return this.repository.getEntityCount();
    }

}
