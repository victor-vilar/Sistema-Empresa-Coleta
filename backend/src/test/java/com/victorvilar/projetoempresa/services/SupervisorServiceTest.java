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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Supervisor service tests class")
class SupervisorServiceTest {

    @InjectMocks
    private SupervisorService supervisorService;

    @Mock
    private SupervisorRepository supervisorRepository;

    @Mock
    private  CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private SupervisorMapper mapper;

    Customer cpfCustomer;
    Supervisor supervisor1;
    Supervisor supervisor2;
    SupervisorCreateDto supervisorCreateDto1;
    SupervisorCreateDto supervisorCreateDto2;
    SupervisorResponseDto supervisorResponseDto1;
    SupervisorResponseDto supervisorResponseDto2;
    SupervisorUpdateDto supervisorUpdateDto1;



    @BeforeEach
    void setUp(){

        cpfCustomer = new Customer.CustomerBuilder()
                .nameCompanyName("cpfCustomer")
                .cpfCnpj("86570192051")
                .build();

        supervisor1 = Supervisor.builder()
                .name("supervisor1")
                .role("super1")
                .phoneNumber("1111")
                .email("super1@super.com.br")
                .customer(cpfCustomer)
                .build();

        supervisorCreateDto1 = SupervisorCreateDto.builder()
                .name("supervisor1")
                .role("super1")
                .phoneNumber("1111")
                .email("super1@super.com.br")
                .customerId(cpfCustomer.getCpfCnpj())
                .build();

        supervisor2 = Supervisor.builder()
                .name("supervisor2")
                .role("super2")
                .phoneNumber("2222")
                .email("super2@super.com.br")
                .customer(cpfCustomer)
                .build();

        supervisorCreateDto2 = SupervisorCreateDto.builder()
                .name("supervisor2")
                .role("super2")
                .phoneNumber("2222")
                .email("super2@super.com.br")
                .customerId(cpfCustomer.getCpfCnpj())
                .build();

        supervisorResponseDto1 = SupervisorResponseDto.builder()
                .name("supervisor1 - response")
                .role("super1")
                .phoneNumber("1111")
                .email("super1@super.com.br")
                .customerId(cpfCustomer.getCpfCnpj())
                .build();

        supervisorResponseDto2 = SupervisorResponseDto.builder()
                .name("supervisor2 - response")
                .role("super2")
                .phoneNumber("2222")
                .email("super2@super.com.br")
                .customerId(cpfCustomer.getCpfCnpj())
                .build();

        supervisorUpdateDto1 = SupervisorUpdateDto.builder()
                .id(1L)
                .name("supervisor1")
                .role("super1")
                .phoneNumber("1111")
                .email("super1@super.com.br")
                .customerId(cpfCustomer.getCpfCnpj())
                .build();

    }

    @Test
    @DisplayName("get all when successfull")
    public void getAll_whenSuccessfull(){
        when(this.supervisorRepository.findAll())
                .thenReturn(List.of(supervisor1,supervisor2));
        when(this.mapper.toSupervisorResponseDtoList(List.of(supervisor1,supervisor2)))
            .thenReturn(List.of(supervisorResponseDto1,supervisorResponseDto2));

        List<SupervisorResponseDto> list = this.supervisorService.getAll();
        Assertions.assertEquals(2,list.size());
        Assertions.assertEquals(supervisorResponseDto1,list.get(0));
        Assertions.assertEquals(supervisorResponseDto2,list.get(1));
    }

    @Test
    @DisplayName("get all by customer id successfull")
    public void getAllByCustomer_whenSuccessfull(){
        when(this.supervisorRepository.findByCustomerCpfCnpj(cpfCustomer.getCpfCnpj()))
                .thenReturn(List.of(supervisor1,supervisor2));
        when(this.mapper.toSupervisorResponseDtoList(List.of(supervisor1,supervisor2)))
                .thenReturn(List.of(supervisorResponseDto1,supervisorResponseDto2));

        List<SupervisorResponseDto> list = this.supervisorService.getAllByCustomer(cpfCustomer.getCpfCnpj());
        Assertions.assertEquals(list.get(0).getCustomerId(),cpfCustomer.getCpfCnpj());
        Assertions.assertEquals(list.get(1).getCustomerId(),cpfCustomer.getCpfCnpj());
    }

    @Test
    @DisplayName("get all by customer id when customer id not found")
    public void getAllByCustomer_ReturnEmpty_whenNotFoundCustomer(){
        when(this.supervisorRepository.findByCustomerCpfCnpj(cpfCustomer.getCpfCnpj()))
                .thenReturn(anyList());
        List<SupervisorResponseDto> list = this.supervisorService.getAllByCustomer(cpfCustomer.getCpfCnpj());
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("find by customer id when successfull")
    public void findBySupervisorId_whenSuccessfull(){
        when(this.supervisorRepository.findById(1L))
                .thenReturn(Optional.of(supervisor1));
        Supervisor sup = this.supervisorService.findSupervisorById(1L);
        assertEquals(sup,supervisor1);
    }

    @Test
    @DisplayName("find by customer id when not found customer id")
    public void findBySupervisorId_whenNotFoundCustomerId(){
        when(this.supervisorRepository.findById(1L))
                .thenThrow(new SupervisorNotFoundException("Supervisor Not Found !"));
        SupervisorNotFoundException exception =
                Assertions.assertThrows(SupervisorNotFoundException.class,() ->
        this.supervisorService.findSupervisorById(1L));
        Assertions.assertEquals(exception.getClass(), SupervisorNotFoundException.class);
        Assertions.assertEquals(exception.getMessage(),"Supervisor Not Found !");

    }

    @Test
    @DisplayName("get by id when successfull")
    public void getById_whenSuccessfull(){
        when(this.supervisorRepository.findById(1L))
                .thenReturn(Optional.of(supervisor1));
        when(this.mapper.toSupervisorResponseDto(supervisor1))
                .thenReturn(supervisorResponseDto1);

        SupervisorResponseDto sup = this.supervisorService.getById(1L);
        Assertions.assertEquals(sup,supervisorResponseDto1);


    }

    @Test
    @DisplayName("get by id throws SupervisorNotFoundException when supervisor not found")
    public void getById_throwsSupervisorNotFound(){
        when(this.supervisorRepository.findById(1L))
                .thenThrow(new SupervisorNotFoundException("Supervisor Not Found !"));
        SupervisorNotFoundException exception =
                Assertions.assertThrows(SupervisorNotFoundException.class,() ->
                        this.supervisorService.getById(1L));
        Assertions.assertEquals(exception.getClass(), SupervisorNotFoundException.class);
        Assertions.assertEquals(exception.getMessage(),"Supervisor Not Found !");
    }

    @Test
    @DisplayName("save when successfull")
    public void save_WhenSuccessfull(){
        when(this.customerService.findCustomerById(supervisor1.getCustomer().getCpfCnpj()))
                .thenReturn(cpfCustomer);
        when(this.mapper.toSupervisor(supervisorCreateDto1))
                .thenReturn(supervisor1);
        when(this.mapper.toSupervisorResponseDto(any()))
                .thenReturn(supervisorResponseDto1);

        SupervisorResponseDto srdto = this.supervisorService.save(supervisorCreateDto1);

        verify(this.customerRepository,times(1)).save(cpfCustomer);
        verify(this.supervisorRepository,times(1)).save(any(Supervisor.class));

        assertEquals(srdto,supervisorResponseDto1);

    }

    @Test
    @DisplayName("save when customer id not found")
    public void save_WhenCustomerIdNotFound(){
        when(this.supervisorRepository.findById(1L))
                .thenThrow(new SupervisorNotFoundException("Supervisor Not Found !"));
        SupervisorNotFoundException exception =
                Assertions.assertThrows(SupervisorNotFoundException.class,() ->
                        this.supervisorService.getById(1L));
        Assertions.assertEquals(exception.getClass(), SupervisorNotFoundException.class);
        Assertions.assertEquals(exception.getMessage(),"Supervisor Not Found !");
    }

    @Test
    @DisplayName("delete when successfull")
    public void delete_WhenSuccessfull(){
        this.supervisorService.delete(anyLong());
        verify(this.supervisorRepository,times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("update when successfull")
    public void update_WhenSuccessfull(){

        when(this.supervisorRepository.findById(anyLong())).thenReturn(Optional.of(supervisor1));
        when(this.supervisorRepository.save(any(Supervisor.class))).thenReturn(supervisor1);
        when(this.mapper.toSupervisorResponseDto(any(Supervisor.class))).thenReturn(supervisorResponseDto1);

        SupervisorResponseDto spr = this.supervisorService.update(supervisorUpdateDto1);
        verify(this.supervisorRepository,times(1)).save(any(Supervisor.class));
        assertEquals(spr.getCustomerId(), supervisorUpdateDto1.getCustomerId());
        assertEquals(spr.getName(), supervisorResponseDto1.getName());


    }





}