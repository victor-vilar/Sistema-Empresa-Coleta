package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.Address;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.dto.adress.AddressCreateDto;
import com.victorvilar.projetoempresa.dto.adress.AddressDto;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.adress.AddressUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AddressMapperTest {

    @Autowired
    private AddressMapper mapper;

    private AddressCreateDto createDto;
    private AddressUpdateDto updateDto;
    private AddressResponseDto responseDto;
    private Address address;
    private Address address2;
    private Customer customer;



    @BeforeEach
    void setUp(){
        customer = new Customer("82508641073","EMPRESA1");
        address = new Address(1L,"ENDERECO1","10","10","111111","CIDADE1","CD",customer,true);
        address2 = new Address(2L,"ENDERECO2","20","20","222222","CIDADE2","CD",customer,true);
        updateDto = new AddressUpdateDto();

    }

    @Test
    void toAddress_WhenPassingCreateDto() {
        createDto = new AddressCreateDto("ENDERECO1","10","10","25085858","CIDADE1","CD",true,"82508641073");
        Address address1 = this.mapper.toAddress(createDto);
        assertEquals(address1.getClass(),address.getClass());
        compare(createDto,address1);
    }

    @Test
    void toAddress_WhenPassingUpdateDto() {
        updateDto = new AddressUpdateDto(1L,"ENDERECO1","10","10","25085858","CIDADE1","CD",true,"82508641073");
        Address address1 = this.mapper.toAddress(updateDto);
        assertEquals(address1.getClass(),address.getClass());
        assertEquals(address1.getId(),updateDto.getId());
        compare(updateDto,address1);
    }


    @Test
    void toAddressResponseDto() {
        responseDto = this.mapper.toAddressResponseDto(address);
        compare(responseDto,address);
    }

    @Test
    void toAddressResponseDtoList() {
        List<AddressResponseDto> responses = this.mapper.toAddressResponseDtoList(List.of(address,address2));
        List<Address> addresses = List.of(address,address2);
        assertNotNull(responses);
        assertEquals(addresses.size(),responses.size());
        for(int i = 0 ; i < addresses.size(); i ++){
            assertEquals(responses.get(i).getId(),addresses.get(i).getId());
            assertEquals(responses.get(i).getCustomer(),address.getCustomer().getCpfCnpj());
            compare(responses.get(i),addresses.get(i));
        }
    }

    private void compare(AddressDto responseDto, Address address){
        assertEquals(responseDto.getAddressName(),address.getAddressName());
        assertEquals(responseDto.getAddressNumber(),address.getAddressNumber());
        assertEquals(responseDto.getComplement(),address.getComplement());
        assertEquals(responseDto.getCity(),address.getCity());
        assertEquals(responseDto.getZipCode(),address.getZipCode());
        assertEquals(responseDto.getState(),address.getState());
        assertEquals(responseDto.isRequiresCollection(),address.isRequiresCollection());

    }


}