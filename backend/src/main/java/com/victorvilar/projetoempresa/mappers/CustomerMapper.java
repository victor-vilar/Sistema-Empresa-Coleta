package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.dto.customer.CustomerCreateDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseDto;
import com.victorvilar.projetoempresa.domain.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private final ModelMapper mapper;

    @Autowired
    public CustomerMapper(ModelMapper map){
        this.mapper = map;
    }

    public Customer toCustomer(CustomerCreateDto clientCreate){
        return this.mapper.map(clientCreate, Customer.class);
    }

    public CustomerResponseDto toCustomerResponseDto(Customer customer){
        return this.mapper.map(customer, CustomerResponseDto.class);
    }

    public List<CustomerResponseDto> toCustomerResponseDtoList(List<Customer> clientes){
        return clientes.stream().map(e -> toCustomerResponseDto(e)).collect(Collectors.toList());
    }
}
