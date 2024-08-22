package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderRequestDto;
import com.victorvilar.projetoempresa.enums.ContractStatus;
import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import com.victorvilar.projetoempresa.enums.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceOrderMapperTest {

    @Autowired
    private ServiceOrderMapper mapper;

    ServiceOrder serviceOrder1;
    ServiceOrder serviceOrder2;
    ServiceOrderCreateDto serviceOrderCreateDto;
    ServiceOrderUpdateDto serviceOrderUpdateDto;
    Customer customer;
    Contract contract;
    ItemContract itemContract;
    
    @BeforeEach
    public void setUp() {

        customer = Customer.builer()
                .nameCompanyName("Empresa")
                .cpfCnpj("123456")
                .build();

        contract = Contract.builder()
                .customer(customer)
                .contractStatus(ContractStatus.ATIVO)
                .beginDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        itemContract = new ItemContract();
        contract.addNewItem(itemContract);
        itemContract.setMeasurementUnit(MeasurementUnit.LITROS);
        itemContract.setCollectionFrequency(new CollectionFrequency(1L, Set.of(Weekday.SEGUNDA),"SEMANAL"));


        serviceOrder1 = ServiceOrder.builder()
                .id(1L)
                .emissionDate(LocalDate.now())
                .serviceExpectedDate(LocalDate.now())
                .itemContract(itemContract)
                .customer(customer)
                .address(new Address())
                .build();

        serviceOrder2 = ServiceOrder.builder()
                .id(2L)
                .serviceExpectedDate(LocalDate.of(2024, 8, 21))
                .serviceExecutedDate(LocalDate.of(2024, 8, 21))
                .itemContract(itemContract)
                .customer(customer)
                .address(new Address())
                .amountCollected(12L)
                .ineaManifest("123456")
                .serviceTime(LocalTime.now())
                .observation("O trabalho foi foda hoje")
                .build();

        serviceOrderCreateDto = new ServiceOrderCreateDto();
        serviceOrderCreateDto.setServiceExpectedDate(LocalDate.of(2024, 8, 21));
        serviceOrderCreateDto.setServiceExecutedDate(LocalDate.of(2024, 8, 21));
        serviceOrderCreateDto.setItemContract(1L);
        serviceOrderCreateDto.setAddress(1L);

        serviceOrderUpdateDto = new ServiceOrderUpdateDto();
        serviceOrderUpdateDto.setId(1L);
        serviceOrderUpdateDto.setServiceExpectedDate(LocalDate.of(2024,8,21));
        serviceOrderUpdateDto.setServiceExecutedDate(LocalDate.of(2024,8,21));
        serviceOrderUpdateDto.setServiceTime(LocalTime.now());
        serviceOrderUpdateDto.setItemContract(1L);
        serviceOrderUpdateDto.setAddress(1L);
        serviceOrderUpdateDto.setAmountCollected(10L);
        serviceOrderUpdateDto.setIneaManifest("111");
        serviceOrderUpdateDto.setObservation("finalizado");
        serviceOrderUpdateDto.setOsFileUrl("/os/");
        serviceOrderUpdateDto.setServiceOrderStatus(1);

    }

    @Test
    void toServiceOrder_WhenPassingCreateDto() {
        ServiceOrder order = mapper.toServiceOrder(serviceOrderCreateDto);
        compare(serviceOrderCreateDto,order);
    }

    @Test
    void toServiceOrder_WhenPassingUpdateDto() {
        ServiceOrder order = mapper.toServiceOrder(serviceOrderUpdateDto);
        compare(serviceOrderUpdateDto,order);
    }

    @Test
    void toServiceOrderResponseDto() {
        ServiceOrderResponseDto response1 = mapper.toServiceOrderResponseDto(serviceOrder1);
        ServiceOrderResponseDto response2 = mapper.toServiceOrderResponseDto(serviceOrder2);
        compare(response1,serviceOrder1);
        compare(response2,serviceOrder2);

    }


    @Test
    void toServiceResponseDtoList() {
    }

    void compare(ServiceOrderDto dto, ServiceOrder order){
        assertEquals(dto.getEmissionDate(),order.getEmissionDate());
        assertEquals(dto.getServiceExpectedDate(),order.getServiceExpectedDate());
        assertEquals(dto.getServiceExecutedDate(),order.getServiceExecutedDate());
        assertEquals(dto.getObservation(),order.getObservation());
        assertEquals(dto.getServiceTime(),order.getServiceTime());
        assertEquals(dto.getAmountCollected(),order.getAmountCollected());
        assertEquals(dto.getIneaManifest(),order.getIneaManifest());
        assertEquals(dto.getOsFileUrl(),order.getOsFileUrl());
    }

    void compareRequestDto(ServiceOrderRequestDto dto, ServiceOrder order){
        compare(dto,order);
    }

    void compareResponseDto(ServiceOrderResponseDto dto, ServiceOrder order){
        compare(dto,order);
    }
}