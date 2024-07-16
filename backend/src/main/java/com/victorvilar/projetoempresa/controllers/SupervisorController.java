package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.dto.supervisor.SupervisorCreateDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorResponseDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorUpdateDto;
import com.victorvilar.projetoempresa.mappers.SupervisorMapper;
import com.victorvilar.projetoempresa.services.CustomerService;
import com.victorvilar.projetoempresa.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/supervisors")
public class SupervisorController {

    private final SupervisorService service;
    private final CustomerService customerService;
    private final SupervisorMapper mapper;

    @Autowired
    public SupervisorController(SupervisorService service,
                                CustomerService customerService,
                                SupervisorMapper mapper){
        this.service = service;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    /**
     * Get all supervisors
     * @return return a list of supervisorResponseDTo
     */
    @GetMapping()
    public ResponseEntity<List<SupervisorResponseDto>> getAll(){
        return ResponseEntity.ok(this.service.getAll());
    }

    /**
     * get all supervisores by client id
      * @param customerId client id
     * @return list of supervisores of that client
     */
    @GetMapping("by-customer/{customerId}")
    public ResponseEntity<List<SupervisorResponseDto>> getAllByCustomer(@PathVariable String customerId){
        return ResponseEntity.ok(this.service.getAllByCustomer(customerId));
    }

    /**
     * Get supervisor by id
     * @param id of a supervisor
     * @return ResponseEntity of SupervisorResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<SupervisorResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.getById(id));
    }

    /**
     * Add a new supervisor for a customer
     * @return http response
     */
    @PostMapping()
    public ResponseEntity<SupervisorResponseDto> save(@Valid  @RequestBody SupervisorCreateDto supervisoCreateDto){
        return ResponseEntity.ok(this.service.save(supervisoCreateDto));

    }


    @DeleteMapping("{supervisorId}")
    public ResponseEntity<Void> delete(@PathVariable Long supervisorId){
        this.service.delete(supervisorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<SupervisorResponseDto> update(@Valid @RequestBody SupervisorUpdateDto supervisorUpdateDto){
        return ResponseEntity.ok(this.service.update(supervisorUpdateDto));
    }

}
