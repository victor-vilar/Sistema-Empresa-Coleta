package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Residue;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueCreateDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueResponseDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueUpdateDto;
import com.victorvilar.projetoempresa.dto.residuetype.interfaces.ResidueDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResidueMapperTest {

    @Autowired
    private ResidueMapper mapper;

    Residue residue1;
    Residue residue2;
    ResidueCreateDto residueCreateDto;
    ResidueUpdateDto residueUpdateDto;


    @BeforeEach
    void setUp(){
        residue1 = new Residue(1L,"Infectante","Residue infectante");
        residue2 = new Residue(2L,"Extraordinário","Residue extraordinário");
        residueCreateDto = new ResidueCreateDto("Entulho","Residuo entulho");
        residueUpdateDto = new ResidueUpdateDto(1L,"Entulho","Residuo entulho");
    }

    @Test
    void toResidue_WhenPassingCreateDto() {
        Residue residue = mapper.toResidue(residueCreateDto);
        compare(residueCreateDto,residue);
    }

    @Test
    void toResidue_WhenPassingUpdateDto() {
        Residue residue = mapper.toResidue(residueUpdateDto);
        assertEquals(residueUpdateDto.getId(),residue.getId());
        compare(residueUpdateDto,residue);
    }

    @Test
    void toResidueResponseDto() {
        ResidueResponseDto dto1 = mapper.toResidueResponseDto(residue1);
        ResidueResponseDto dto2 = mapper.toResidueResponseDto(residue2);
        compare(dto1,residue1);
        compare(dto2,residue2);

    }

    @Test
    void toResidueResponseDtoList() {
        List<ResidueResponseDto> dtos = mapper.toResidueResponseDtoList(List.of(residue1,residue2));
        List<Residue> residues = List.of(residue1,residue2);
        for(int i = 0; i < dtos.size(); i++){
            assertEquals(dtos.get(i).getId(),residues.get(i).getId());
            compare(dtos.get(i),residues.get(i));
        }

    }

    void compare(ResidueDto dto, Residue residue){
        assertEquals(dto.getType(),residue.getType());
        assertEquals(dto.getDescription(),residue.getDescription());
    }

}