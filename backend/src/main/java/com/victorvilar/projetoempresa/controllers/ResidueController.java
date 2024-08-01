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
@RequestMapping("/v1/residues")
public class ResidueController {

    private final ResidueService service;
    private final ResidueMapper mapper;

    @Autowired
    public ResidueController(ResidueService service, ResidueMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public ResponseEntity<List<ResidueResponseDto>> getAll(){
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidueResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<ResidueResponseDto> save(@Valid @RequestBody ResidueCreateDto residue){
        return ResponseEntity.ok(this.service.save(residue));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ResidueResponseDto> udpate(@Valid @RequestBody ResidueUpdateDto residue){
        return new ResponseEntity<>(this.service.udpate(residue),HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getEntityCount(){
        return ResponseEntity.ok(this.service.getEntityCount());
    }

}
