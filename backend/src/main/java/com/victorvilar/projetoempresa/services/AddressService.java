package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.dto.adress.AddressCreateDto;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.adress.AddressUpdateDto;
import com.victorvilar.projetoempresa.domain.Address;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.exceptions.AddressNotFoundException;
import com.victorvilar.projetoempresa.mappers.AddressMapper;
import com.victorvilar.projetoempresa.repository.AddressRepository;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;
    private final AddressMapper addressMapper;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    //constructor
    public AddressService(AddressRepository repository, AddressMapper mapper, CustomerService customerService
    , CustomerRepository customerRepository) {
        this.repository = repository;
        this.addressMapper = mapper;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @Cacheable(value="addresses")
    public List<AddressResponseDto> getAll(){
        return this.addressMapper.toAddressResponseDtoList(this.repository.findAll());
    }

    @Cacheable(value="addresses",key="#clientId")
    public List<AddressResponseDto> getAllByCustomerId(String clientId){
        return this.addressMapper.toAddressResponseDtoList(this.repository.findByCustomerCpfCnpj(clientId));
    }

    public Address findAddressById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new AddressNotFoundException("Address Not found"));
    }

    public AddressResponseDto getById(Long id){
        return this.addressMapper.toAddressResponseDto(this.findAddressById(id));
    }

    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public AddressResponseDto save(AddressCreateDto addressCreateDto){
        Address address = this.addressMapper.toAddress(addressCreateDto);
        Customer customer = this.customerService.findCustomerById(addressCreateDto.getCustomerId());
        customer.addNewAddress(address);
        return this.addressMapper.toAddressResponseDto(this.repository.save(address));
    }

    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public void delete(Long id){
        Address address = this.findAddressById(id);
        this.repository.deleteById(id);
    }

    @Transactional
    @CacheEvict(value = "addresses", allEntries = true)
    public AddressResponseDto update(AddressUpdateDto addressUpdateDto){
        Address addressToUpdate = this.repository.findById(addressUpdateDto.getId()).orElseThrow(() -> new AddressNotFoundException("Address Not found"));
        addressToUpdate.setAddressName(addressUpdateDto.getAddressName());
        addressToUpdate.setAddressNumber(addressUpdateDto.getAddressNumber());
        addressToUpdate.setCity(addressUpdateDto.getCity());
        addressToUpdate.setComplement(addressUpdateDto.getComplement());
        addressToUpdate.setState(addressUpdateDto.getState());
        addressToUpdate.setZipCode(addressUpdateDto.getZipCode());
        addressToUpdate.setRequiresCollection(addressUpdateDto.isRequiresCollection());
        return this.addressMapper.toAddressResponseDto(this.repository.save(addressToUpdate));
    }

    public Integer getEntityCount(){
        return this.repository.getEntityCount();
    }



}

