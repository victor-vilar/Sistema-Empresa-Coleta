package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.Residue;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueCreateDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueResponseDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueUpdateDto;
import com.victorvilar.projetoempresa.exceptions.ResidueNotFoundException;
import com.victorvilar.projetoempresa.mappers.ResidueMapper;
import com.victorvilar.projetoempresa.repository.ResidueRepository;
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
class ResidueServiceTest {

    @InjectMocks
    @Spy
    ResidueService residueService;

    @Mock
    ResidueRepository repository;

    @Mock
    ResidueMapper mapper;

    Residue residue1;
    Residue residue2;
    ResidueCreateDto residueCreateDto;
    ResidueUpdateDto residueUpdateDto;
    ResidueResponseDto residueResponseDto1;
    ResidueResponseDto residueResponseDto2;

    @BeforeEach
    void setUp() {

        residue1 = new Residue(1L,"EXTRAORDINÁRIO","CLASSE IIA");
        residue2 = new Residue(2L,"ENTULHO","CLASS IIB");
        residueCreateDto = new ResidueCreateDto();

        residueResponseDto1 = new ResidueResponseDto(1L,"EXTRAORDINÁRIO","CLASSE IIA");
        residueResponseDto2 = new ResidueResponseDto(2L,"ENTULHO","CLASS IIB");
        residueUpdateDto = new ResidueUpdateDto(1L,"INFECTANTE","CLASSE IA");

    }

    @Test
     void getAll_WhenSuccessfull(){
        Mockito.when(repository.findAll()).thenReturn(List.of(residue1,residue2));
        Mockito.when(mapper.toResidueResponseDtoList(List.of(residue1,residue2))).thenReturn(List.of(residueResponseDto1
        ,residueResponseDto2));

        List<ResidueResponseDto> residueResponseList = residueService.getAll();
        assertFalse(residueResponseList.isEmpty());
        assertEquals(residueResponseList.size(),2);
        assertEquals(residueResponseList.get(0).getType(),residue1.getType());
        assertEquals(residueResponseList.get(1).getType(),residue2.getType());
        verify(repository,times(1)).findAll();
    }

    @Test
    public void getById_WhenSuccessfull(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(residue1));
        Mockito.when(mapper.toResidueResponseDto(residue1)).thenReturn(residueResponseDto1);
        ResidueResponseDto residueResponseDto = residueService.getById(1L);
        assertEquals(residueResponseDto.getId(),residue1.getId());
        assertEquals(residueResponseDto.getType(),residue1.getType());
        assertEquals(residueResponseDto.getDescription(),residue1.getDescription());

    }

    @Test
    public void getById_WhenPassAnInvalidId(){
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
        ResidueNotFoundException exception = Assertions.assertThrows(ResidueNotFoundException.class, () -> this.residueService.getById(anyLong()));
        assertEquals("Residue Not Found !",exception.getMessage());
        assertEquals(exception.getClass(),ResidueNotFoundException.class);
    }

    @Test
    public void findById_WhenSuccessfull(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(residue1));
        Residue residue = residueService.findById(1L);
        assertEquals(residue1.getId(),residue.getId());
        assertEquals(residue1.getType(),residue.getType());
        assertEquals(residue1.getDescription(),residue.getDescription());
    }

    @Test
    public void findById_WhenPassAndInvalidId(){
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
        ResidueNotFoundException exception = Assertions.assertThrows(ResidueNotFoundException.class, () -> this.residueService.findById(anyLong()));
        assertEquals("Residue Not Found !",exception.getMessage());
        assertEquals(exception.getClass(),ResidueNotFoundException.class);
    }

    @Test
    public void save_WhenSuccessfull(){
        when(this.repository.save(residue1)).thenReturn(residue1);
        Mockito.when(mapper.toResidue(residueCreateDto)).thenReturn(residue1);
        Mockito.when(mapper.toResidueResponseDto(residue1)).thenReturn(residueResponseDto1);
        ResidueResponseDto residueResponseDto = residueService.save(residueCreateDto);
        assertEquals(residueResponseDto.getId(),residueResponseDto1.getId());
        assertEquals(residueResponseDto.getType(),residueResponseDto1.getType());
        assertEquals(residueResponseDto.getDescription(),residueResponseDto1.getDescription());
    }

    @Test
    public void delete_WhenSuccessfull(){
        doReturn(residue1).when(residueService).findById(1L);
        residueService.delete(1L);
        verify(repository,times(1)).delete(residue1);
    }

    @Test
    public void update_WhenSuccessfull(){
        doReturn(residue1).when(residueService).findById(1L);
        when(repository.save(residue1)).thenReturn(residue1);
        when(mapper.toResidueResponseDto(residue1)).thenReturn(residueResponseDto1);
        ResidueResponseDto residueResponseDto = residueService.update(residueUpdateDto);
        assertEquals(residueResponseDto.getId(),residueResponseDto1.getId());
        assertEquals(residueResponseDto.getType(),residueResponseDto1.getType());
        assertEquals(residueResponseDto.getDescription(),residueResponseDto1.getDescription());
    }

    @Test
    public void getEntityCount_WhenSuccessfull(){
        when(repository.getEntityCount()).thenReturn(1);
        int qtd = residueService.getEntityCount();
        assertEquals(1,qtd);
        verify(repository,times(1)).getEntityCount();
    }
}