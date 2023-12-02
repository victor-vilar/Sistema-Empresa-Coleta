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
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService){
        this.equipmentService = equipmentService;

    }

    /**
     * get all equipments
     * @return a list of equipments
     */
    @GetMapping("")
    public ResponseEntity<List<EquipmentResponseDto>> getAll(){
        return ResponseEntity.ok(this.equipmentService.getAll());
    }

    /**
     * get equipment by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.equipmentService.getById(id));
    }

    /**
     * save a new equipment
     * @param equipmentCreateDto equipmentCreateDto to save
     */
    @PostMapping("")
    public ResponseEntity<EquipmentResponseDto> save(@Valid @RequestBody EquipmentCreateDto equipmentCreateDto){
        return ResponseEntity.ok(this.equipmentService.save(equipmentCreateDto));
    }

    /**
     * update a equipment
     * @param equipmentUpdateDto equipment instance to get the new data
     * @return equipment updated
     */
    @PutMapping
    public ResponseEntity<EquipmentResponseDto> update(@Valid @RequestBody EquipmentUpdateDto equipmentUpdateDto){
        return ResponseEntity.ok(this.equipmentService.update(equipmentUpdateDto));
    }

    /**
     * delete a equipment
     * @param id id to update
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.equipmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
