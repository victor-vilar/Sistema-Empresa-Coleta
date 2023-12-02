package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.dto.adress.AddressCreateDto;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.adress.AddressUpdateDto;
import com.victorvilar.projetoempresa.domain.Address;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.mappers.AddressMapper;
import com.victorvilar.projetoempresa.repository.AddressRepository;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Contract service tests class")
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository repository;

    @Mock
    private AddressMapper mapper;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    Customer customer;
    Customer anotherCustomer;
    Address address1;
    AddressResponseDto addressResponseDto1;
    Address address2;
    AddressResponseDto addressResponseDto2;
    AddressCreateDto addressCreateDto1;

    @BeforeEach
    void setUp() {

        customer = new Customer.CustomerBuilder()
                .cpfCnpj("59213337000")
                .nameCompanyName("TEST CUSTOMER")
                .build();

        anotherCustomer = new Customer.CustomerBuilder()
                .cpfCnpj("40204340004")
                .nameCompanyName("TEST CUSTOMER")
                .build();

        address1 = Address
                .builder()
                .addressName("address 1 - name")
                .addressNumber("address 1 - number")
                .complement("address 1 - complement")
                .zipCode("address 1 - zipCode ")
                .city("address 1 - city")
                .state("address 1 - state")
                .requiresCollection(true)
                .build();


        customer.addNewAddress(address1);
        addressResponseDto1 = new AddressResponseDto(
                1L,address1.getAddressName(),address1.getAddressNumber(),
                address1.getComplement(),address1.getZipCode(),
                address1.getCity(),address1.getState(),
                address1.getClient().getCpfCnpj(),
                address1.isRequiresCollection()
        );
        addressResponseDto1.setCustomerId(address1.getClient().getCpfCnpj());

        address2 = Address
                .builder()
                .addressName("address 2 - name")
                .addressNumber("address 2 - number")
                .complement("address 2 - complement")
                .zipCode("address 2 - zipCode ")
                .city("address 2 - city")
                .state("address 2 - state")
                .requiresCollection(true)
                .build();

        anotherCustomer.addNewAddress(address2);
        addressResponseDto2 = new AddressResponseDto();
        addressResponseDto2.setCustomerId(address2.getClient().getCpfCnpj());

        addressCreateDto1 = AddressCreateDto
                .builder()
                .addressName("address create1 - name")
                .addressNumber("address create1 - number")
                .complement("address create1 - complement")
                .zipCode("address create1 - zipCode ")
                .city("address create1 - city")
                .state("address create1 - state")
                .requiresCollection(true)
                .build();

        addressCreateDto1.setCustomerId(address1.getClient().getCpfCnpj());
    }

    @Test
    @DisplayName("Get a list of Address when successfull")
    public void getAll_WhenSuccessfull(){
        when(repository.findAll()).thenReturn(List.of(address1,address2));
        when(mapper.toAddressResponseDtoList(List.of(address1,address2))).thenReturn(List.of(new AddressResponseDto(),new AddressResponseDto()));

        List<AddressResponseDto> list = this.addressService.getAll();
        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(2,list.size());
    }


    @Test
    @DisplayName("Get all by customer id when successfull")
    public void getAllByCustomerId_WhenSuccessfull(){
        when(repository.findByCustomerCpfCnpj("59213337000")).thenReturn(List.of(address1));
        when(mapper.toAddressResponseDtoList(List.of(address1))).thenReturn(List.of(addressResponseDto1));

        List<AddressResponseDto>list = this.addressService.getAllByCustomerId("59213337000");
        Assertions.assertNotNull(list);
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(1,list.size());
        Assertions.assertEquals(customer.getCpfCnpj(),list.get(0).getCustomerId());
    }

    @Test
    @DisplayName("Save when successfull")
    public void save_WhenSuccessfull(){

        when(mapper.toAddress(addressCreateDto1)).thenReturn(address1);
        when(customerService.findCustomerById("59213337000")).thenReturn(customer);
        when(repository.save(address1)).thenReturn(address1);
        when(mapper.toAddressResponseDto(address1)).thenReturn(addressResponseDto1);

        AddressResponseDto savedAddress = this.addressService.save(addressCreateDto1);
        Assertions.assertNotNull(savedAddress);
        Assertions.assertEquals(savedAddress.getClass(),addressResponseDto1.getClass());
        Assertions.assertEquals(savedAddress.getAddressName(),addressResponseDto1.getAddressName());
    }

    @Test
    @DisplayName("Update when successfull")
    public void update_WhenSuccessfull(){

        Address addressToUpdate = new Address();
        addressToUpdate.setId(1L);

        when(this.repository.findById(any())).thenReturn(Optional.of(addressToUpdate));
        when(this.repository.save(any())).thenReturn(address1);
        when(this.mapper.toAddressResponseDto(any())).thenReturn(addressResponseDto1);

        AddressResponseDto updatedAddress = this.addressService.update(new AddressUpdateDto());
        Assertions.assertNotNull(updatedAddress);
        Assertions.assertEquals(updatedAddress.getClass(),addressResponseDto1.getClass());
        Assertions.assertEquals(updatedAddress.getAddressName(),addressResponseDto1.getAddressName());

    }

    @Test
    @DisplayName("Delete when successfull")
    public void delete_WhenSuccessfull(){
        address1.setId(1L);
        when(this.repository.findById(anyLong())).thenReturn(Optional.of(address1));
        this.addressService.delete(2L);
        verify(this.repository,times(1)).deleteById(2L);
    }

}