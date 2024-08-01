package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.dto.adress.AddressCreateDto;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.adress.AddressUpdateDto;
import com.victorvilar.projetoempresa.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/addresses")
public class AddressController {

    private final AddressService service;

    @Autowired
    public AddressController(AddressService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<AddressResponseDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll());
    }

    @GetMapping("by-customer/{clientId}")
    public ResponseEntity<List<AddressResponseDto>> getAllByCustomerId(@PathVariable String clientId){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAllByCustomerId(clientId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<AddressResponseDto> save(@Valid @RequestBody AddressCreateDto addressCreateDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.save(addressCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<AddressResponseDto> update(@RequestBody AddressUpdateDto addressUpdateDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(addressUpdateDto));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getEntityCount(){
        return ResponseEntity.ok(this.service.getEntityCount());
    }

}
