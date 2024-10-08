package com.victorvilar.projetoempresa.services;

import java.util.List;

import com.victorvilar.projetoempresa.business.rules.customer.CustomerRegisterRuler;
import com.victorvilar.projetoempresa.dto.customer.CustomerCreateDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseDefaultImplDto;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerResponseDto;
import com.victorvilar.projetoempresa.exceptions.CustomerNotFoundException;
import com.victorvilar.projetoempresa.exceptions.CpfOrCnpjAlreadyExistsException;
import com.victorvilar.projetoempresa.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.victorvilar.projetoempresa.exceptions.InvalidCpfOrCnpjException;
import com.victorvilar.projetoempresa.repository.CustomerRepository;

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
	

	@Cacheable(value="customers")
	public List<CustomerResponseDto> getAll() {
		return this.mapper.toCustomerResponseDtoList(this.repository.findAll());
	}

	public Customer findCustomerById(String id) throws CustomerNotFoundException {
		return this.repository.findByCpfCnpj(id).orElseThrow(() ->new CustomerNotFoundException("Customer Not Found !"));
	}

	@Cacheable(value="customers", key="#id")
	public CustomerResponseDto getById(String id){
		Customer customer = this.findCustomerById(id);
		return this.mapper.toCustomerResponseDto(customer);
	}

	@Transactional
	@CacheEvict(value="customers",allEntries = true)
	public CustomerResponseDto save(CustomerCreateDto customerCreateDto) throws InvalidCpfOrCnpjException, CpfOrCnpjAlreadyExistsException {
		Customer customer = this.mapper.toCustomer(customerCreateDto);
		this.customerRegisterRulers.forEach(rule -> rule.verification(customer,this.repository));
		customer.setNameCompanyName(customer.getNameCompanyName().toUpperCase());
		return this.mapper.toCustomerResponseDto(this.repository.save(customer));
	}

	@Transactional
	@CacheEvict(value="customers",allEntries = true)
	public CustomerResponseDto update(CustomerCreateDto customerCreateDto) {
		Customer customer = findCustomerById(customerCreateDto.getCpfCnpj());
		customer.setCpfCnpj(customerCreateDto.getCpfCnpj());
		customer.setNameCompanyName(customerCreateDto.getNameCompanyName());
		return this.mapper.toCustomerResponseDto(repository.save(customer));
	}

	@Transactional
	@CacheEvict(value="customers",allEntries = true)
	public void delete(String id) {
		//if the id is not found will throw a exception
		findCustomerById(id);
		repository.deleteById(id);
	}

	public Integer getEntityCount(){
		return this.repository.getEntityCount();
	}

}
