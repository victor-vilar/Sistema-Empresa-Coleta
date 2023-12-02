package com.victorvilar.projetoempresa.controllers;

import java.util.List;

import com.victorvilar.projetoempresa.dto.customer.CustomerCreateDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseDto;
import com.victorvilar.projetoempresa.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.victorvilar.projetoempresa.services.CustomerService;

import jakarta.validation.Valid;

/**
 * A client contoller
 * @author Victor Vilar
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

	
	private final CustomerService service;

	@Autowired
	public CustomerController(CustomerService service, CustomerMapper mapper) {
		this.service = service;

	}

	/**
	 * return all clients of repository
	 * @return a listOfResponseDto
	 */
	@GetMapping()
	public ResponseEntity<List<CustomerResponseDto>> getAll(){
		return ResponseEntity.ok(this.service.getAll());
	}

	/**
	 * return a customer by id
	 * @param id id of the customer
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> getById(@PathVariable String id) {
		return ResponseEntity.ok(this.service.getById(id));
	}

	/**
	 * Add new customer
	 * @param customerDto
	 * @return
	 */
	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponseDto> save(@Valid @RequestBody CustomerCreateDto customerDto){
		return ResponseEntity.ok(this.service.save(customerDto));

	}

	/**
	 * Update customer by id
	 * @param customerDto
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<CustomerResponseDto> update(@RequestBody CustomerCreateDto customerDto){
		return ResponseEntity.ok(this.service.update(customerDto));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}