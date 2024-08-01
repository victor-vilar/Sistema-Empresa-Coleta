package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.dto.equipment.EquipmentCreateDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentResponseDto;
import com.victorvilar.projetoempresa.dto.equipment.EquipmentUpdateDto;
import com.victorvilar.projetoempresa.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/equipments")
public class EquipmentController {

    private final EquipmentService service;

    @Autowired
    public EquipmentController(EquipmentService service){
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<EquipmentResponseDto>> getAll(){
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<EquipmentResponseDto> save(@Valid @RequestBody EquipmentCreateDto equipmentCreateDto){
        return ResponseEntity.ok(this.service.save(equipmentCreateDto));
    }

    @PutMapping
    public ResponseEntity<EquipmentResponseDto> update(@Valid @RequestBody EquipmentUpdateDto equipmentUpdateDto){
        return ResponseEntity.ok(this.service.update(equipmentUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getEntityCount(){
        return ResponseEntity.ok(this.service.getEntityCount());
    }


}
