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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    //constructor
    public AddressService(AddressRepository addressRepository, AddressMapper mapper, CustomerService customerService
    ,CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = mapper;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }
    //------------

    /**
     * get all address
     * @return all address
     */
    public List<AddressResponseDto> getAll(){
        return this.addressMapper.toAddressResponseDtoList(this.addressRepository.findAll());
    }

    /**
     *  get all address of a client
     * @param clientId
     * @return
     */
    public List<AddressResponseDto> getAllByCustomerId(String clientId){
        return this.addressMapper.toAddressResponseDtoList(this.addressRepository.findByCustomerCpfCnpj(clientId));
    }

    /**
     * get a Address Object without mapping
     * @return Address Object
     */
    public Address findAddressById(Long id){
        return this.addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("Address Not found"));
    }

    /**
     * get an address by id
     * @param id
     * @return
     */
    public AddressResponseDto getById(Long id){
        return this.addressMapper.toAddressResponseDto(this.findAddressById(id));
    }

    /**
     * create a new address
     * @param addressCreateDto
     */
    public AddressResponseDto save(AddressCreateDto addressCreateDto){
        Address address = this.addressMapper.toAddress(addressCreateDto);
        Customer customer = this.customerService.findCustomerById(addressCreateDto.getCustomerId());
        customer.addNewAddress(address);
        return this.addressMapper.toAddressResponseDto(this.addressRepository.save(address));
    }


    /**
     * delete an address
     * @param id
     */
    public void delete(Long id){
        Address address = this.findAddressById(id);
        this.addressRepository.deleteById(id);
    }


    /**
     * update an address
     * @param addressUpdateDto
     * @return saved contract
     */
    public AddressResponseDto update(AddressUpdateDto addressUpdateDto){
        Address addressToUpdate = this.addressRepository.findById(addressUpdateDto.getId()).orElseThrow(() -> new AddressNotFoundException("Address Not found"));
        addressToUpdate.setAddressName(addressUpdateDto.getAddressName());
        addressToUpdate.setAddressNumber(addressUpdateDto.getAddressNumber());
        addressToUpdate.setCity(addressUpdateDto.getCity());
        addressToUpdate.setComplement(addressUpdateDto.getComplement());
        addressToUpdate.setState(addressUpdateDto.getState());
        addressToUpdate.setZipCode(addressUpdateDto.getZipCode());
        addressToUpdate.setRequiresCollection(addressUpdateDto.isRequiresCollection());
        this.addressRepository.save(addressToUpdate);
        return this.addressMapper.toAddressResponseDto(addressToUpdate);
    }



}

