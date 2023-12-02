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

    private final SupervisorRepository supervisorRespository;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final SupervisorMapper mapper;

    @Autowired
    public SupervisorService(SupervisorRepository supervisorRespository,
                             CustomerService customerService,
                             SupervisorMapper mapper,
                             CustomerRepository customerRepository){
        this.supervisorRespository = supervisorRespository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    /**
     * get all supervisors
     * @return
     */
    public List<SupervisorResponseDto> getAll() {
        return this.mapper.toSupervisorResponseDtoList(this.supervisorRespository.findAll());
    }

    /**
     * get all supervisores of a client
     * @param clientId id of a client
     * @return
     */
    public List<SupervisorResponseDto> getAllByCustomer(String clientId) {
        return this.mapper.toSupervisorResponseDtoList(this.supervisorRespository.findByCustomerCpfCnpj(clientId));
    }

    /**
     * find supervisor by id
     * @param id id of supervisor
     * @return
     */
    public Supervisor findSupervisorById(Long id){
        return this.supervisorRespository.findById(id).orElseThrow(() -> new SupervisorNotFoundException("Supervisor Not Found !"));
    }

    /**
     * find supervisor by id and return as SupervisorResponseDto
     * @param id id of supervisor
     * @return
     */
    public SupervisorResponseDto getById(Long id){
        return this.mapper.toSupervisorResponseDto(
                this.supervisorRespository.findById(id)
                        .orElseThrow(() -> new SupervisorNotFoundException("Supervisor Not Found !"))
        );
    }


    /**
     * add a new supervisor for a client
     * @param supervisorCreateDto
     */
    @Transactional
    public SupervisorResponseDto save(SupervisorCreateDto supervisorCreateDto){
        Customer customer = this.customerService.findCustomerById(supervisorCreateDto.getCustomerId());
        Supervisor supervisor = mapper.toSupervisor(supervisorCreateDto);
        customer.addNewSupervisor(supervisor);
        this.supervisorRespository.save(supervisor);
        this.customerRepository.save(customer);
        return this.mapper.toSupervisorResponseDto(supervisor);
    }

    /**
     * delete a supervisor
     * @param supervisorId supervisor id
     */
    @Transactional
    public void delete(Long supervisorId) {
        this.supervisorRespository.deleteById(supervisorId);
    }

    /**
     * Update supervisor data
     * @param supervisorUpdateDto supervisor new data
     *
     */

    @Transactional
    public SupervisorResponseDto update(SupervisorUpdateDto supervisorUpdateDto) {
        Supervisor supervisorToUpdate = findSupervisorById(supervisorUpdateDto.getId());
        supervisorToUpdate.setName(supervisorUpdateDto.getName());
        supervisorToUpdate.setRole(supervisorUpdateDto.getRole());
        supervisorToUpdate.setEmail(supervisorUpdateDto.getEmail());
        supervisorToUpdate.setPhoneNumber(supervisorUpdateDto.getPhoneNumber());
        return this.mapper.toSupervisorResponseDto(this.supervisorRespository.save(supervisorToUpdate));
    }
}
