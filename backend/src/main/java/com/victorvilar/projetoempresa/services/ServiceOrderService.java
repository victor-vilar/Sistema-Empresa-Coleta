package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.exceptions.AddressNotFoundException;
import com.victorvilar.projetoempresa.exceptions.ItemContractNotFoundException;
import com.victorvilar.projetoempresa.exceptions.ServiceOrderNotFoundException;
import com.victorvilar.projetoempresa.mappers.ServiceOrderMapper;
import com.victorvilar.projetoempresa.repository.ItemContractRepository;
import com.victorvilar.projetoempresa.repository.ServiceOrderRepository;
import com.victorvilar.projetoempresa.services.interfaces.EntityOfCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Vector;

@Service
public class ServiceOrderService implements EntityOfCustomerService<ServiceOrderCreateDto, ServiceOrderUpdateDto, ServiceOrderResponseDto> {


    private final ServiceOrderRepository repository;
    private final ServiceOrderMapper mapper;
    private final ItemContractRepository itemContractRepository;

    @Autowired
    public ServiceOrderService(
            ServiceOrderRepository repository,
            ServiceOrderMapper mapper,
            ItemContractRepository itemContractRepository){
        this.repository = repository;
        this.mapper = mapper;
        this.itemContractRepository = itemContractRepository;

    }

    @Override
    public List<ServiceOrderResponseDto> getAll() {
        return this.mapper.toServiceResponseDtoList(this.repository.findAll());
    }

    @Override
    public List<ServiceOrderResponseDto> getAllByCustomerId(String customerId) {
        return this.mapper.toServiceResponseDtoList(this.repository.findByCustomerCpfCnpj(customerId));
    }

    public List<ServiceOrderResponseDto> getAllByItemContract(Long itemId){
        //TODO
        return null;
    }

    @Override
    public ServiceOrderResponseDto getById(Long id) {
        return this.mapper.toServiceOrderResponseDto(this.repository.findById(id).orElseThrow(() -> new ServiceOrderNotFoundException("Service Order Not Found !")));
    }

    public List<ServiceOrderResponseDto> getNotExecuted() {
        List<ServiceOrder> list = this.repository.getNotExecutedList();
        return this.mapper.toServiceResponseDtoList(list);
    }

    public Integer getNotExecutedCount(){
        return this.repository.getNotExecutedCount();
    }

    public Integer getEntityCount(){
        return this.repository.getEntityCount();
    }

    public ServiceOrder findById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new ServiceOrderNotFoundException("Service Order Not Found !"));
    }

    @Override
    public ServiceOrderResponseDto save(ServiceOrderCreateDto createDto) {
        ServiceOrder serviceOrder = this.mapper.toServiceOrder(createDto);
        setRelationalProperties(serviceOrder, createDto);
        return this.mapper.toServiceOrderResponseDto(this.repository.save(serviceOrder));
    }

    @Override
    public ServiceOrderResponseDto update(ServiceOrderUpdateDto updateDto) {
        ServiceOrder order = this.findById(updateDto.getId());
        this.setRelationalProperties(order,updateDto);
        this.updateProperties(order,updateDto);
        return this.mapper.toServiceOrderResponseDto(this.repository.save(order));
    }

    private void updateProperties(ServiceOrder order, ServiceOrderUpdateDto dto){
        order.setIneaManifest(dto.getIneaManifest());
        order.setServiceTime(dto.getServiceTime());
        order.setObservation(dto.getObservation());
        order.setOsFileUrl(dto.getOsFileUrl());
        order.setAmountCollected(dto.getAmountCollected());
        order.setServiceExecutedDate(dto.getServiceExecutedDate());
    }


    @Transactional
    public void delete(List<Long> ids){
        this.repository.deleteAllById(ids);
    }

    private void setRelationalProperties(ServiceOrder serviceOrder, ServiceOrderDto dto){
        ItemContract itemContract = findSelectedItemContract(dto.getItemContract());
        Customer customer = itemContract.getContract().getCustomer();
        Address address = findSelectedAddress(customer.getAddresses(),dto.getAddress());
        Vehicle vehicle = findSelectedVehicle(dto.getVehicle());

        serviceOrder.setItemContract(itemContract);
        serviceOrder.setCustomer(customer);
        serviceOrder.setAddress(address);
        serviceOrder.setVehicle(vehicle);
    }

    private ItemContract findSelectedItemContract(Long id){
        Optional<ItemContract> itemContract = this.itemContractRepository.findById(id);
        if(itemContract.isEmpty()){
            throw new ItemContractNotFoundException("ItemContract Not Found");
        }
        return itemContract.get();
    }

    private Address findSelectedAddress(List<Address> addresses , Long id){
        Optional<Address> address =
                 addresses
                .stream()
                .filter(add -> Objects.equals(add.getId(), id))
                .findFirst();

        if(address.isPresent()) return address.get();
        throw new AddressNotFoundException("Address Not Found");
    }

    private Vehicle findSelectedVehicle(Long id){
        //todo
        return null;
    }


}
