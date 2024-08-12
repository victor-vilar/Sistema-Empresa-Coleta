package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.business.rules.customer.CustomerAlreadyExistRuler;
import com.victorvilar.projetoempresa.business.rules.customer.CustomerCpfCnpjNotValid;
import com.victorvilar.projetoempresa.business.rules.customer.CustomerRegisterRuler;
import com.victorvilar.projetoempresa.dto.customer.CustomerCreateDto;
import com.victorvilar.projetoempresa.dto.customer.CustomerResponseDefaultImplDto;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.dto.customer.interfaces.CustomerResponseDto;
import com.victorvilar.projetoempresa.exceptions.CustomerNotFoundException;
import com.victorvilar.projetoempresa.mappers.CustomerMapper;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Customer service tests class")
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @Mock
    private List<CustomerRegisterRuler> rules = Arrays.asList(new CustomerAlreadyExistRuler(),new CustomerCpfCnpjNotValid());



    CustomerCreateDto cpfCustomer;
    CustomerCreateDto cnpjCustomer;
    CustomerCreateDto wrongCpfCustomer;
    CustomerCreateDto wrongCnpjCustomer;
    Customer nullCpfCnpjCustomer;
    Customer nullNameCompanyNameCustomer;
    CustomerCreateDto customerDto;


    @BeforeEach
    void setUp(){
        cpfCustomer = new CustomerCreateDto.CustomerCreateDtoBuilder()
                .nameCompanyName("cpfCustomer")
                .cpfCnpj("86570192051")
                .build();


        cnpjCustomer = new CustomerCreateDto.CustomerCreateDtoBuilder()
                .nameCompanyName("cnpjCustomer")
                .cpfCnpj("48153741000139")
                .build();


        wrongCpfCustomer = new CustomerCreateDto.CustomerCreateDtoBuilder()
                .nameCompanyName("cnpjCustomer")
                .cpfCnpj("86570192055")
                .build();

        wrongCnpjCustomer = new CustomerCreateDto.CustomerCreateDtoBuilder()
                .nameCompanyName("cnpjCustomer")
                .cpfCnpj("48153741000133")
                .build();

        nullCpfCnpjCustomer = new Customer.CustomerBuilder()
                .nameCompanyName("cnpjCustomer")
                .build();


        nullNameCompanyNameCustomer = new Customer.CustomerBuilder()
                .cpfCnpj("48153741000133")
                .build();

        customerDto = new CustomerCreateDto.CustomerCreateDtoBuilder()
                .nameCompanyName("dtoCustomer")
                .cpfCnpj("48153741000139")
                .build();

    }



    @Test
    @DisplayName("addNewCustomer  successfully when passing cpf")
    void addNewCustomer_Successfully_WhenPassingValidCpf(){


        when(mapper.toCustomer(any(CustomerCreateDto.class))).thenReturn(new Customer(cpfCustomer.getCpfCnpj(),cpfCustomer.getNameCompanyName(),true));
        when(repository.save(any(Customer.class))).thenReturn(new Customer(cpfCustomer.getCpfCnpj(),cpfCustomer.getNameCompanyName(),true));
        when(mapper.toCustomerResponseDto(any(Customer.class))).thenReturn(new CustomerResponseDefaultImplDto(cpfCustomer.getCpfCnpj(),cpfCustomer.getNameCompanyName(),true));

        CustomerResponseDto savedCustomer = customerService.save(cpfCustomer);
        assertEquals(cpfCustomer.getCpfCnpj(),savedCustomer.getCpfCnpj());
        assertEquals(cpfCustomer.getNameCompanyName(), savedCustomer.getNameCompanyName());
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("save successfully when passing cnpj")
    void addNewCustomer_Successfully_WhenPassingValidCnpj(){

        when(mapper.toCustomer(any(CustomerCreateDto.class))).thenReturn(new Customer(cnpjCustomer.getCpfCnpj(),cnpjCustomer.getNameCompanyName(),true));
        when(repository.save(any(Customer.class))).thenReturn(new Customer(cnpjCustomer.getCpfCnpj(),cnpjCustomer.getNameCompanyName(),true));
        when(mapper.toCustomerResponseDto(any(Customer.class))).thenReturn(new CustomerResponseDefaultImplDto(cnpjCustomer.getCpfCnpj(),cnpjCustomer.getNameCompanyName(),true));

        CustomerResponseDto savedCustomer = customerService.save(cnpjCustomer);
        assertEquals(cnpjCustomer.getCpfCnpj(),savedCustomer.getCpfCnpj());
        assertEquals(cnpjCustomer.getNameCompanyName(), savedCustomer.getNameCompanyName());
        Mockito.verifyNoMoreInteractions(repository);
    }


    @Test
    @DisplayName("delete throw CustomerNotFoundException when cpf/cnpj doesn't exist")
    void delete_ThrowsCustomerNotFoundException_WhenCpfCnpjIsWrong(){
        Assertions.assertThrows(CustomerNotFoundException.class,() ->this.customerService.delete("1234"));
    }


    @Test
    @DisplayName("find customer by cpfCnpj when successfull")
    void find_Successfully_WhenPassValidCpfCnpj(){

        when(repository.findByCpfCnpj(Mockito.anyString())).thenReturn(Optional.of(new Customer(cpfCustomer.getCpfCnpj(),cpfCustomer.getNameCompanyName(),true)));
        Customer savedCustomer = customerService.findCustomerById("86570192051");
        assertEquals(cpfCustomer.getCpfCnpj(),savedCustomer.getCpfCnpj());
        assertEquals(cpfCustomer.getNameCompanyName(),savedCustomer.getNameCompanyName());
        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("find throw CustomerNotFoundException when cpf/cnpj is wrong")
    void find_ThrowsCustomerNotFoundException_WhenCpfCnpjIsWrong(){
        when(repository.findByCpfCnpj(Mockito.anyString())).thenReturn(Optional.empty());
        CustomerNotFoundException exception = Assertions.assertThrows(CustomerNotFoundException.class,() ->this.customerService.findCustomerById("86570192051"));
        assertEquals(exception.getMessage(),"Customer Not Found !");
        assertNotEquals(exception,null);
        assertEquals(exception.getClass(), CustomerNotFoundException.class);
        Mockito.verifyNoMoreInteractions(repository);
    }


    @Test
    @DisplayName("update customer when successfull")
    void update_Successfully_WhenPassValidCpfCnpj(){
        when(repository.findByCpfCnpj(Mockito.anyString())).thenReturn(Optional.of(Mockito.mock(Customer.class)));
        when(repository.save(Mockito.any(Customer.class))).thenReturn(new Customer(cpfCustomer.getCpfCnpj(),cpfCustomer.getNameCompanyName(),true));
        when(mapper.toCustomerResponseDto(any(Customer.class))).thenReturn(new CustomerResponseDefaultImplDto(cpfCustomer.getCpfCnpj(), cpfCustomer.getNameCompanyName(),true));
        CustomerResponseDto updatedCustomer = this.customerService.update(cpfCustomer);
        assertNotNull(updatedCustomer);
        assertEquals(updatedCustomer.getNameCompanyName(),cpfCustomer.getNameCompanyName());
        assertEquals(updatedCustomer.getCpfCnpj(),cpfCustomer.getCpfCnpj());

    }


}