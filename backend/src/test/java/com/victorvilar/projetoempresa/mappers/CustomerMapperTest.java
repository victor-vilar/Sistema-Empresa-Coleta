package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.dto.customer.CustomerCreateDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseDefaultImplDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseSimpleDto;
import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerDto;
import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerMapperTest {

    @Autowired
    private CustomerMapper mapper;

    CustomerCreateDto customerCreateDto;
    Customer customer1;
    Customer customer2;

    @BeforeEach
    public void setUp(){
        customerCreateDto = CustomerCreateDto.builder().cpfCnpj("123456789").nameCompanyName("company1").build();
        customer1 = Customer.builer().cpfCnpj("234567891").nameCompanyName("company2").isActive(true).build();
        customer2 = Customer.builer().cpfCnpj("345678912").nameCompanyName("company3").isActive(false).build();
    }


    @Test
    void toCustomer_WhenPassingCustomerCreateDto() {
            Customer customer = mapper.toCustomer(customerCreateDto);
            compare(customerCreateDto,customer);
    }

    @Test
    void toCustomerResponseDto() {
        CustomerResponseDto dto1 = mapper.toCustomerResponseDto(customer1);
        CustomerResponseDto dto2 = mapper.toCustomerResponseDto(customer2);
        compare(dto1,customer1);
        compare(dto2,customer2);
    }

    @Test
    void toCustomerResponseDto_PassingDtoType() {
        CustomerResponseDto dto1 = mapper.toCustomerResponseDto(customer1, CustomerResponseDefaultImplDto.class);
        CustomerResponseDto dto2 = mapper.toCustomerResponseDto(customer2, CustomerResponseDefaultImplDto.class);
        CustomerResponseDto dto3 = mapper.toCustomerResponseDto(customer1, CustomerResponseSimpleDto.class);
        CustomerResponseDto dto4 = mapper.toCustomerResponseDto(customer2, CustomerResponseSimpleDto.class);

        compare(dto1,customer1);
        compare(dto1, CustomerResponseDefaultImplDto.class);
        compare(dto2,customer2);
        compare(dto2, CustomerResponseDefaultImplDto.class);
        compare(dto3,customer1);
        compare(dto3, CustomerResponseSimpleDto.class);
        compare(dto4,customer2);
        compare(dto4, CustomerResponseSimpleDto.class);

    }

    @Test
    void toCustomerResponseDtoList() {
        List<CustomerResponseDto> dtos = mapper.toCustomerResponseDtoList(List.of(customer1,customer2));
        List<Customer> customes = List.of(customer1,customer2);
        for(int i = 0; i < customes.size();i++){
            compare(dtos.get(i),customes.get(i));
        }

    }
    void compare(CustomerDto dto ,Class<?> className){
        assertEquals(dto.getClass(), className);
    }

    void compare(CustomerDto dto, Customer customer){
        assertEquals(dto.isActive(),customer.isActive());
        assertEquals(dto.getCpfCnpj(),customer.getCpfCnpj());
        assertEquals(dto.getNameCompanyName(),customer.getNameCompanyName());
    }

}