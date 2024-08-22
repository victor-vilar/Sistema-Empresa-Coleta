package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.domain.ServiceOrder;
import com.victorvilar.projetoempresa.domain.Vehicle;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDefaultImplDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.exceptions.ServiceOrderNotFoundException;
import com.victorvilar.projetoempresa.mappers.ServiceOrderMapper;
import com.victorvilar.projetoempresa.repository.ItemContractRepository;
import com.victorvilar.projetoempresa.repository.ServiceOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Service order service tests class")
class ServiceOrderServiceTest {

    @InjectMocks
    private ServiceOrderService serviceOrderService;

    @Mock
    private ServiceOrderMapper serviceOrderMapper;

    @Mock
    private ServiceOrderRepository serviceOrderRepository;

    @Mock
    private ItemContractRepository itemContractRepository;

    ServiceOrder so1;
    ServiceOrder so2;
    ServiceOrderResponseDefaultImplDto sord1 = new ServiceOrderResponseDefaultImplDto();
    ServiceOrderResponseDefaultImplDto sord2 = new ServiceOrderResponseDefaultImplDto();
    ItemContract itemContract;
    //Contract contract;
    Customer customer;

    @BeforeEach
    public void setUp() {



        customer = Customer.builer()
                .cpfCnpj("123456")
                .nameCompanyName("empresa1")
                .build();

        this.so1 = ServiceOrder.builder()
                .id(1L)
                .emissionDate(LocalDate.now())
                .serviceExpectedDate(LocalDate.now().plusDays(5))
                .vehicle(new Vehicle())
                .build();

        this.so2 = ServiceOrder.builder()
                .id(2L)
                .emissionDate(LocalDate.now())
                .serviceExpectedDate(LocalDate.now().plusDays(5))
                .vehicle(new Vehicle())
                .build();

        this.sord1.setId(1L);
        this.sord1.setEmissionDate(LocalDate.now());

        this.sord2.setId(2L);
        this.sord2.setEmissionDate(LocalDate.now());


    }

    @Test
    @DisplayName("Return successfully when get all orders")
    public void getAll_WhenSuccessfull() {
        List<ServiceOrderResponseDefaultImplDto> list = List.of(sord1,sord2);
        when(this.serviceOrderRepository.findAll()).thenReturn(List.of(so1, so2));
        doReturn(list).when(serviceOrderMapper).toServiceResponseDtoList(List.of(so1, so2));
        List<? extends ServiceOrderResponseDto> orders = this.serviceOrderService.getAll();
        assertFalse(orders.isEmpty());
        assertEquals(orders.size(), 2);
        verify(this.serviceOrderRepository, times(1)).findAll();

    }


    @Test
    @DisplayName("Return successfully a list of service orders when pass a valid cnpj/cpf")
    public void getAllByCustomerId_WhenSuccessfull() {
        List<ServiceOrderResponseDefaultImplDto> list = List.of(sord1,sord2);
        when(this.serviceOrderRepository.findByCustomerCpfCnpj(anyString())).thenReturn(List.of(so1, so2));
        doReturn(list).when(serviceOrderMapper).toServiceResponseDtoList(List.of(so1, so2));
        List<? extends ServiceOrderResponseDto> orders = this.serviceOrderService.getAllByCustomerId("123456789");
        assertFalse(orders.isEmpty());
        assertEquals(orders.size(), 2);
        verify(this.serviceOrderRepository, times(1)).findByCustomerCpfCnpj("123456789");
    }

    @Test
    @DisplayName("Return empty when try to find a list of service order when passing a invalid cnpj/cpf")
    public void getAllByCustomer_ReturnEmpty_WhenPassingInvalidCnpjCpf() {
        when(this.serviceOrderRepository.findByCustomerCpfCnpj(anyString())).thenReturn(Collections.emptyList());
        when(this.serviceOrderMapper.toServiceResponseDtoList(anyList())).thenReturn(Collections.emptyList());
        List<? extends ServiceOrderResponseDto> orders = this.serviceOrderService.getAllByCustomerId("123456789");
        assertTrue(orders.isEmpty());
        assertEquals(orders.size(), 0);
        verify(this.serviceOrderRepository, times(1)).findByCustomerCpfCnpj("123456789");
    }

    @Test
    @DisplayName("Get all orders successfully when pass a valid itemContract")
    public void getAllByItemContract_WhenSuccessfully() {
        //TODO
    }

    @Test
    @DisplayName("Get order by id when successfull")
    public void getById_WhenSuccessfull() {
        when(this.serviceOrderRepository.findById(anyLong())).thenReturn(Optional.of(so1));
        when(this.serviceOrderMapper.toServiceOrderResponseDto(any())).thenReturn(sord1);
        ServiceOrderResponseDto order = this.serviceOrderService.getById(anyLong());
        assertEquals(order.getEmissionDate(), LocalDate.now());
        verify(this.serviceOrderRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Get order by id throws ServiceOrderNotFoundException when pass an invalid id")
    public void getById_ThrowsException_WhenPassInvalidId() {
        when(this.serviceOrderRepository.findById(anyLong())).thenReturn(Optional.empty());
        ServiceOrderNotFoundException exception = Assertions.assertThrows(ServiceOrderNotFoundException.class, () -> this.serviceOrderService.getById(anyLong()));
        assertEquals(exception.getMessage(), "Service Order Not Found !");
        assertNotEquals(exception, null);
        assertEquals(exception.getClass(), ServiceOrderNotFoundException.class);
        Mockito.verifyNoMoreInteractions(serviceOrderRepository);
    }

    @Test
    @DisplayName("Get orders that where not executed yet")
    public void getNotExecuted_WhenSuccessfull(){
        //TODO
    }

    @Test
    @DisplayName("Get not executed count")
    public void getNotExecutedCount_WhenSuccessfull(){
        //todo
    }

    @Test
    @DisplayName("Get total of orders persisted")
    public void getTotalOfOrdersPersisted_WhenSuccessfull(){
        //todo
    }

    @Test
    @DisplayName("Find order by id when successfull, this method do not convert to response dto")
    public void findById_WhenSuccessfull() {
        when(this.serviceOrderRepository.findById(anyLong())).thenReturn(Optional.of(so1));
        ServiceOrder order = this.serviceOrderService.findById(anyLong());
        assertEquals(order.getId(), 1L);
        assertEquals(order.getEmissionDate(), LocalDate.now());
        verify(this.serviceOrderRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Find order by id throws ServiceOrderNotFoundException when pass an invalid id")
    public void findById_ThrowsException_WhenPassInvalidId() {
        when(this.serviceOrderRepository.findById(anyLong())).thenReturn(Optional.empty());
        ServiceOrderNotFoundException exception = Assertions.assertThrows(ServiceOrderNotFoundException.class, () -> this.serviceOrderService.getById(anyLong()));
        assertEquals(exception.getMessage(), "Service Order Not Found !");
        assertNotEquals(exception, null);
        assertEquals(exception.getClass(), ServiceOrderNotFoundException.class);
        Mockito.verifyNoMoreInteractions(serviceOrderRepository);
    }




    @Test
    @DisplayName("Save successfully when pass a valid ServiceOrderCreateDTO")
    public void save_WhenSuccessfull() {
        when(this.serviceOrderMapper.toServiceOrder(any(ServiceOrderCreateDto.class))).thenReturn(so1);
        when(this.serviceOrderRepository.save(so1)).thenReturn(so1);
        when(this.serviceOrderMapper.toServiceOrderResponseDto(so1)).thenReturn(sord1);
        ServiceOrderResponseDto order = this.serviceOrderService.save(new ServiceOrderCreateDto());
        assertEquals(order.getEmissionDate(), LocalDate.now());
        verify(this.serviceOrderRepository, times(1)).save(any(ServiceOrder.class));
    }



    @Test
    @DisplayName("Update a ServiceOrder when successfull")
    public void update_WhenSuccessfull() {
        when(this.serviceOrderMapper.toServiceOrder(any(ServiceOrderUpdateDto.class))).thenReturn(so1);
        when(this.serviceOrderMapper.toServiceOrderResponseDto(any())).thenReturn(sord1);
        ServiceOrderResponseDto order = this.serviceOrderService.update(new ServiceOrderUpdateDto());
        assertEquals(order.getEmissionDate(), LocalDate.now());
        verify(this.serviceOrderRepository, times(1)).save(any(ServiceOrder.class));

    }


    @Test
    @DisplayName("Delete a service order when successfull")
    public void delete_WhenSuccessfull() {
        this.serviceOrderService.delete(List.of(1L, 2L));
        verify(this.serviceOrderRepository, times(1)).deleteAllById(anyList());
    }


}

