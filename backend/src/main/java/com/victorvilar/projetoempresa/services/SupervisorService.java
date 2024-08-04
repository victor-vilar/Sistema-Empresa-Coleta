package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.dto.supervisor.SupervisorCreateDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorResponseDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorUpdateDto;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.domain.Supervisor;
import com.victorvilar.projetoempresa.exceptions.SupervisorNotFoundException;
import com.victorvilar.projetoempresa.mappers.SupervisorMapper;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import com.victorvilar.projetoempresa.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class SupervisorService {

    private final SupervisorRepository repository;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final SupervisorMapper mapper;

    @Autowired
    public SupervisorService(SupervisorRepository repository,
                             CustomerService customerService,
                             SupervisorMapper mapper,
                             CustomerRepository customerRepository){
        this.repository = repository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public List<SupervisorResponseDto> getAll() {
        return this.mapper.toSupervisorResponseDtoList(this.repository.findAll());
    }

    public List<SupervisorResponseDto> getAllByCustomer(String clientId) {
        return this.mapper.toSupervisorResponseDtoList(this.repository.findByCustomerCpfCnpj(clientId));
    }

    public Supervisor findSupervisorById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new SupervisorNotFoundException("Supervisor Not Found !"));
    }

    public SupervisorResponseDto getById(Long id){
        return this.mapper.toSupervisorResponseDto(
                this.repository.findById(id)
                        .orElseThrow(() -> new SupervisorNotFoundException("Supervisor Not Found !"))
        );
    }

    @Transactional
    public SupervisorResponseDto save(SupervisorCreateDto supervisorCreateDto){
        Customer customer = this.customerService.findCustomerById(supervisorCreateDto.getCustomerId());
        Supervisor supervisor = mapper.toSupervisor(supervisorCreateDto);
        customer.addNewSupervisor(supervisor);
        return this.mapper.toSupervisorResponseDto(this.repository.save(supervisor));
    }

    @Transactional
    public void delete(Long supervisorId) {
        this.repository.deleteById(supervisorId);
    }

    @Transactional
    public SupervisorResponseDto update(SupervisorUpdateDto supervisorUpdateDto) {
        Supervisor supervisorToUpdate = findSupervisorById(supervisorUpdateDto.getId());
        supervisorToUpdate.setName(supervisorUpdateDto.getName());
        supervisorToUpdate.setRole(supervisorUpdateDto.getRole());
        supervisorToUpdate.setEmail(supervisorUpdateDto.getEmail());
        supervisorToUpdate.setPhoneNumber(supervisorUpdateDto.getPhoneNumber());
        return this.mapper.toSupervisorResponseDto(this.repository.save(supervisorToUpdate));
    }

    public Integer getEntityCount(){
        return this.repository.getEntityCount();
    }
}
