package com.victorvilar.projetoempresa.controllers;

import java.util.List;

import com.victorvilar.projetoempresa.dto.customer.CustomerCreateDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseDefaultImplDto;
import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerResponseDto;
import com.victorvilar.projetoempresa.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.victorvilar.projetoempresa.services.CustomerService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

	
	private final CustomerService service;

	@Autowired
	public CustomerController(CustomerService service, CustomerMapper mapper) {
		this.service = service;

	}

	@GetMapping()
	public ResponseEntity<List<CustomerResponseDto>> getAll(){
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> getById(@PathVariable String id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponseDto> save(@Valid @RequestBody CustomerCreateDto customerDto){
		return ResponseEntity.ok(this.service.save(customerDto));

	}

	@PutMapping()
	public ResponseEntity<CustomerResponseDto> update(@RequestBody CustomerCreateDto customerDto){
		return ResponseEntity.ok(this.service.update(customerDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<Integer> getEntityCount(){
		return ResponseEntity.ok(this.service.getEntityCount());
	}

}