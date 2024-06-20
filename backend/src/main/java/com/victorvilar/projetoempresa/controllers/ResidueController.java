package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.dto.residuetype.ResidueCreateDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueResponseDto;
import com.victorvilar.projetoempresa.dto.residuetype.ResidueUpdateDto;
import com.victorvilar.projetoempresa.mappers.ResidueMapper;
import com.victorvilar.projetoempresa.services.ResidueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residue")
public class ResidueController {

    private final ResidueService residueService;
    private final ResidueMapper mapper;

    @Autowired
    public ResidueController(ResidueService residueService, ResidueMapper mapper){
        this.residueService = residueService;
        this.mapper = mapper;
    }

    /**
     * get all residues
     * @return a list of ResidueResponseDto
     */
    @GetMapping("")
    public ResponseEntity<List<ResidueResponseDto>> getAll(){
        return ResponseEntity.ok(this.residueService.getAll());
    }

    /**
     * find residue by id
     * @param id id of a residue
     * @return residue
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResidueResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.residueService.getById(id));
    }

    /**
     * add a new residue type
     * @param residue
     */
    @PostMapping("")
    public ResponseEntity<ResidueResponseDto> save(@Valid @RequestBody ResidueCreateDto residue){
        return ResponseEntity.ok(this.residueService.save(residue));

    }

    /**
     * delete a residue by id
     * @param id if of a residue
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.residueService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    /**
     * update a residue
     * @param residue residue type with data
     * @return residue updated
     */
    @PutMapping()
    public ResponseEntity<ResidueResponseDto> udpate(@Valid @RequestBody ResidueUpdateDto residue){
        return new ResponseEntity<>(this.residueService.udpate(residue),HttpStatus.OK);
    }


}
