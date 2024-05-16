package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
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

    public ServiceOrder toServiceOrder(ServiceOrderCreateDto createDto){
        ServiceOrder serviceOrder = this.mapper.map(createDto,ServiceOrder.class);
        this.setBasicProperties(serviceOrder, createDto.getItemContract(),createDto.getAddress());

        return serviceOrder;
    }

    public ServiceOrder toServiceOrder(ServiceOrderUpdateDto updateDto){
        return this.setPropertiesAndUpdate(updateDto);
    }

    public List<ServiceOrder> toServiceOrderListFromServiceOrderCreateDtoList(List<ServiceOrderCreateDto> list){
        return list.stream().map(order -> this.toServiceOrder(order)).toList();
    }

    public List<ServiceOrder> toServiceOrderListFromServiceOrderUpdateDtoList(List<ServiceOrderUpdateDto> list){
        return list.stream().map(order -> this.toServiceOrder(order)).toList();
    }

    public ServiceOrderResponseDto toServiceOrderResponseDto(ServiceOrder order){
        ServiceOrderResponseDto responseDto = this.mapper.map(order,ServiceOrderResponseDto.class);
        responseDto.setCustomerId(order.getCustomer().getCpfCnpj());
        return responseDto;
    }

    public List<ServiceOrderResponseDto> toServiceResponseDtoList(List<ServiceOrder> list ){
        return list.stream().map(order -> this.toServiceOrderResponseDto(order)).toList();
    }

    /**
     * set all properties that can not be null
     *
     * @param serviceOrder service order to set the properties
     * @param item the item contract of the service
     * @param serviceAddress the address where the service is going to be undertake
     * @return service order with properties filled
     */
    private void setBasicProperties(ServiceOrder serviceOrder, Long item, Long serviceAddress){

        ItemContract itemContract = this.itemContractRepository.findById(item).get();
        Customer customer = itemContract.getContract().getCustomer();
        Address address = customer.getAddresses().stream().filter(add -> Objects.equals(add.getId(), serviceAddress)).findFirst().get();

        serviceOrder.setItemContract(itemContract);
        serviceOrder.setCustomer(customer);
        serviceOrder.setAddress(address);

    }

    /**
     * method to get the order from database and update its properties
     *
     * @param updateDto the item with the id and properties to update
     * @return service order updated
     */
    private ServiceOrder setPropertiesAndUpdate(ServiceOrderUpdateDto updateDto){

        //search of the order in database
        ServiceOrder order = this.serviceOrderRepository.findById(updateDto.getId()).orElseThrow(() -> new ServiceOrderNotFoundException("Service Order Not Found !"));

        //set the basic properties, in this case it will update
        this.setBasicProperties(order, updateDto.getItemContract(), updateDto.getAddress());

        if(updateDto.getVehicle() != null){
            Vehicle vehicle = this.vehicleRepository.findById(updateDto.getVehicle()).get();
            order.setVehicle(vehicle);
        }

        order.setIneaManifest(updateDto.getIneaManifest());
        order.setServiceTime(updateDto.getServiceTime());
        order.setObservation(updateDto.getObservation());
        order.setOsFileUrl(updateDto.getOsFileUrl());
        order.setAmountCollected(updateDto.getAmountCollected());
        order.setServiceOrderStatus(updateDto.getServiceOrderStatus());

        return order;

    }


    





}
