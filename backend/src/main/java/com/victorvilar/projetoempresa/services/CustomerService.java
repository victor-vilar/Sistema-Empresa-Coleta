package com.victorvilar.projetoempresa.services;

import java.util.List;

import com.victorvilar.projetoempresa.business.rules.customer.CustomerRegisterRuler;
import com.victorvilar.projetoempresa.dto.customer.CustomerCreateDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseDto;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.exceptions.CustomerNotFoundException;
import com.victorvilar.projetoempresa.exceptions.CpfOrCnpjAlreadyExistsException;
import com.victorvilar.projetoempresa.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorvilar.projetoempresa.exceptions.InvalidCpfOrCnpjException;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import com.victorvilar.projetoempresa.util.CpfCnpjValidator;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

	
	private final CustomerRepository repository;
	private final CustomerMapper mapper;
	private final List<CustomerRegisterRuler> customerRegisterRulers;
	
	@Autowired
	public CustomerService(CustomerRepository repository, CustomerMapper mapper, List<CustomerRegisterRuler> rules) {
		this.repository = repository;
		this.mapper = mapper;
		this.customerRegisterRulers = rules;
	}
	
	/**
	 * get all clients, transform in a clientResponseDto list and return;
	 * @param
	 */
	public List<CustomerResponseDto> getAll() {
		return this.mapper.toCustomerResponseDtoList(this.repository.findAll());
	}
	/**
	 * Return a client with this id, or return null;
	 * @param id
	 * @return
	 */
	public Customer findCustomerById(String id) throws CustomerNotFoundException {
		return this.repository.findByCpfCnpj(id).orElseThrow(() ->new CustomerNotFoundException("Customer Not Found !"));
	}

	/**
	 * Return a customerResponseDto
	 * @param id
	 * @return
	 */
	public CustomerResponseDto getById(String id){
		Customer customer = this.findCustomerById(id);
		return this.mapper.toCustomerResponseDto(customer);
	}

	
	/**
	 * Sing in a new Client
	 * @param customerCreateDto, a client
	 */
	@Transactional
	public CustomerResponseDto save(CustomerCreateDto customerCreateDto) throws InvalidCpfOrCnpjException, CpfOrCnpjAlreadyExistsException {


		Customer customer = this.mapper.toCustomer(customerCreateDto);
		this.customerRegisterRulers.forEach(rule -> rule.verification(customer,this.repository));
		customer.setNameCompanyName(customer.getNameCompanyName().toUpperCase());
		return this.mapper.toCustomerResponseDto(this.repository.save(customer));


	}

	/**
	 * update client
	 * @param customerCreateDto
	 * @return
	 */
	public CustomerResponseDto update(CustomerCreateDto customerCreateDto) {
		Customer customer = findCustomerById(customerCreateDto.getCpfCnpj());
		customer.setCpfCnpj(customerCreateDto.getCpfCnpj());
		customer.setNameCompanyName(customerCreateDto.getNameCompanyName());
		return this.mapper.toCustomerResponseDto(repository.save(customer));
	}


	/**
	 * Delete a client of the database
	 * @param id id of a client
	 */
	@Transactional
	public void delete(String id) {
		//if the id is not found will throw a exception
		findCustomerById(id);
		repository.deleteById(id);
	}


}
