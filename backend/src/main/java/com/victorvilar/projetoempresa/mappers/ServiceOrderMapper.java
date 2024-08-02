package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderDto;
import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;
import com.victorvilar.projetoempresa.exceptions.ServiceOrderNotFoundException;
import com.victorvilar.projetoempresa.repository.ItemContractRepository;
import com.victorvilar.projetoempresa.repository.ServiceOrderRepository;
import com.victorvilar.projetoempresa.repository.VehicleRepository;
import com.victorvilar.projetoempresa.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ServiceOrderMapper {

    private final ModelMapper mapper;
    private final ItemContractRepository itemContractRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerService customerService;
    private final ServiceOrderRepository serviceOrderRepository;


    @Autowired
    public ServiceOrderMapper(ModelMapper mapper,
                              ItemContractRepository itemContractRepository,
                              VehicleRepository vehicleRepository,
                              CustomerService customerService,
                              ServiceOrderRepository serviceOrderRepository){
        this.mapper = mapper;
        this.itemContractRepository = itemContractRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerService= customerService;
        this.serviceOrderRepository = serviceOrderRepository;

    }

    public ServiceOrder toServiceOrder(ServiceOrderDto serviceDto){
        return this.mapper.map(serviceDto,ServiceOrder.class);
    }


    public List<ServiceOrder> toServiceOrder(List<? extends ServiceOrderDto> list){
        return list.stream().map(this::toServiceOrder).toList();
    }


    public ServiceOrderResponseDto toServiceOrderResponseDto(ServiceOrder order){
        ServiceOrderResponseDto responseDto = this.mapper.map(order,ServiceOrderResponseDto.class);
        responseDto.setCustomerId(order.getCustomer().getCpfCnpj());
        return responseDto;
    }

    public List<ServiceOrderResponseDto> toServiceResponseDtoList(List<ServiceOrder> list ){
        return list.stream().map(order -> this.toServiceOrderResponseDto(order)).toList();
    }


    private void setBasicProperties(ServiceOrder serviceOrder, Long item, Long serviceAddress){

        ItemContract itemContract = this.itemContractRepository.findById(item).get();
        Customer customer = itemContract.getContract().getCustomer();
        Address address = customer.getAddresses().stream().filter(add -> Objects.equals(add.getId(), serviceAddress)).findFirst().get();

        serviceOrder.setItemContract(itemContract);
        serviceOrder.setCustomer(customer);
        serviceOrder.setAddress(address);

    }


    private ServiceOrder setPropertiesAndUpdate(ServiceOrderUpdateDto updateDto){

        //search of the order in database
        ServiceOrder order = this.serviceOrderRepository.findById(updateDto.getId()).orElseThrow(() -> new ServiceOrderNotFoundException("Service Order Not Found !"));

        //set the basic properties, in this case it will update
        this.setBasicProperties(order, updateDto.getItemContract(), updateDto.getAddress());

        if(updateDto.getVehicle() != null){
            Vehicle vehicle = this.vehicleRepository.findById(updateDto.getVehicle()).get();
            order.setVehicle(vehicle);
        }

        if(updateDto.getAmountCollected() != null && updateDto.getServiceExecutedDate() != null){
            order.setServiceOrderStatus(ServiceOrderStatus.DONE);
        }else{
            order.setServiceOrderStatus(ServiceOrderStatus.UNDONE);
        }

        order.setIneaManifest(updateDto.getIneaManifest());
        order.setServiceTime(updateDto.getServiceTime());
        order.setObservation(updateDto.getObservation());
        order.setOsFileUrl(updateDto.getOsFileUrl());
        order.setAmountCollected(updateDto.getAmountCollected());
        order.setServiceExecutedDate(updateDto.getServiceExecutedDate());



        return order;

    }


    





}
