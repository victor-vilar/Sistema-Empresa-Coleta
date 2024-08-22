package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.controllers.interfaces.EntityOfCustomerController;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.services.ServiceOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/service-orders")
public class ServiceOrderController {


    private ServiceOrderService service;

    public ServiceOrderController(ServiceOrderService orderService){
        this.service = orderService;
    }

    @GetMapping()
    public ResponseEntity<List< ? extends ServiceOrderResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll());
    }

    @GetMapping("by-customer/{customerId}")
    public ResponseEntity<List<? extends ServiceOrderResponseDto>> getAllByCustomerId(String customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAllByCustomerId(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponseDto> getById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<ServiceOrderResponseDto> save(ServiceOrderCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.save(createDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        this.service.delete(Arrays.asList(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping()
    public ResponseEntity<ServiceOrderResponseDto> update(@Valid ServiceOrderUpdateDto updateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(updateDto));
    }


    @GetMapping("/count")
    public ResponseEntity<Integer> getEntityCount(){
        return ResponseEntity.ok(this.service.getEntityCount());
    }

    @GetMapping("/opened")
    public ResponseEntity<List<? extends ServiceOrderResponseDto>> getNotExecuted(){
        return ResponseEntity.ok(this.service.getNotExecuted());
    }

    @GetMapping("/opened/count")
    public Integer getNotExecutedCount(){
        return this.service.getNotExecutedCount();
    }
}
