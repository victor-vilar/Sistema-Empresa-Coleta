package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.dto.contract.ContractCreateDto;
import com.victorvilar.projetoempresa.dto.contract.ContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.contract.ContractUpdateDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractCreateDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ContractResponseDto;
import com.victorvilar.projetoempresa.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/contracts")
public class ContractController {

    private final ContractService service;

    @Autowired
    public ContractController(ContractService service){
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ContractResponseDto>> getAll(){
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/all/{clientId}")
    public ResponseEntity<List<ContractResponseDto>> getAllByCustomerId(@PathVariable String clientId){
        return ResponseEntity.ok(this.service.getAllByCustomerId(clientId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<ContractResponseDto> save(@Valid @RequestBody ContractCreateDto contract) {
        return ResponseEntity.ok(this.service.save(contract));
    }

    @PostMapping("/additem/{contractId}")
    public ResponseEntity<ContractResponseDto> addNewItemToContract(@PathVariable Long contractId, @Valid @RequestBody ItemContractCreateDto itemDto){
        ContractResponseDto contractResponseDto = this.service.addNewItemToContract(contractId,itemDto);
        return ResponseEntity.ok(contractResponseDto);
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity<?> delete(@PathVariable Long contractId ){
        this.service.delete(contractId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteitens")
    public ResponseEntity<?> deleteItemContract(@RequestBody List<Long> itemId){
        this.service.deleteItemContract(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ContractResponseDto> update(@RequestBody ContractUpdateDto contractUpdateDto){
        return ResponseEntity.ok(this.service.update(contractUpdateDto));

    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getEntityCount(){
        return ResponseEntity.ok(this.service.getEntityCount());
    }

}
