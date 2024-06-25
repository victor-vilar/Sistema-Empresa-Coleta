package com.victorvilar.projetoempresa.controllers;

import com.victorvilar.projetoempresa.controllers.interfaces.EntityOfCustomerController;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.services.ServiceOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/service-order")
public class ServiceOrderController implements EntityOfCustomerController<ServiceOrderCreateDto,ServiceOrderUpdateDto, ServiceOrderResponseDto> {


    private ServiceOrderService serviceOrderService;

    public ServiceOrderController(ServiceOrderService orderService){
        this.serviceOrderService = orderService;
    }

    @Override
    public ResponseEntity<List<ServiceOrderResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceOrderService.getAll());
    }

    @Override
    public ResponseEntity<List<ServiceOrderResponseDto>> getAllByCustomerId(String customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceOrderService.getAllByCustomerId(customerId));
    }

    @Override
    public ResponseEntity<ServiceOrderResponseDto> getById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceOrderService.getById(id));
    }

    @Override
    public ResponseEntity<ServiceOrderResponseDto> save(ServiceOrderCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceOrderService.save(createDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<ServiceOrderResponseDto>> save(@Valid @RequestBody List<ServiceOrderCreateDto> createDtoList){
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceOrderService.save(createDtoList));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        this.serviceOrderService.delete(Arrays.asList(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ServiceOrderResponseDto> update(@Valid ServiceOrderUpdateDto updateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceOrderService.update(updateDto));
    }


    @PutMapping("/all")
    public ResponseEntity<List<ServiceOrderResponseDto>> update(List<ServiceOrderUpdateDto> updateDtoList){
        return ResponseEntity.status(HttpStatus.OK).body(this.serviceOrderService.update(updateDtoList));
    }
}
