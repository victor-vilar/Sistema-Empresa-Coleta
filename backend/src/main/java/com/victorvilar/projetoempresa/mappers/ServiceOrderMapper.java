package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDefaultImplDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceOrderMapper {

    private final ModelMapper mapper;

    @Autowired
    public ServiceOrderMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public ServiceOrder toServiceOrder(ServiceOrderDto serviceDto){
        return this.mapper.map(serviceDto,ServiceOrder.class);
    }

    public ServiceOrderResponseDto toServiceOrderResponseDto(ServiceOrder order){
        ServiceOrderResponseDefaultImplDto responseDto = this.mapper.map(order, ServiceOrderResponseDefaultImplDto.class);
        responseDto.setCustomerId(order.getCustomer().getCpfCnpj());
        return responseDto;
    }

    public ServiceOrderResponseDto toServiceOrder(ServiceOrder order , Class<? extends ServiceOrderResponseDto>dtoType){
        return this.mapper.map(order,dtoType);
    }

    public List<? extends ServiceOrderResponseDto> toServiceResponseDtoList(List<ServiceOrder> list ){
        return list.stream().map(this::toServiceOrderResponseDto).toList();
    }

    public List<? extends ServiceOrderResponseDto> toServiceResponseDtoList(List<ServiceOrder> list, Class<? extends ServiceOrderResponseDto> dtoType ){
        return list.stream().map(this::toServiceOrderResponseDto).toList();
    }






    





}
