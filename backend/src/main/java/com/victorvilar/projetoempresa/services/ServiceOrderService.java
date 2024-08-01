package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.ServiceOrder;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.exceptions.ServiceOrderNotFoundException;
import com.victorvilar.projetoempresa.mappers.ServiceOrderMapper;
import com.victorvilar.projetoempresa.repository.ServiceOrderRepository;
import com.victorvilar.projetoempresa.services.interfaces.EntityOfCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOrderService implements EntityOfCustomerService<ServiceOrderCreateDto, ServiceOrderUpdateDto, ServiceOrderResponseDto> {


    private final ServiceOrderRepository repository;
    private final ServiceOrderMapper mapper;

    @Autowired
    public ServiceOrderService(ServiceOrderRepository repository,ServiceOrderMapper mapper){
        this.repository = repository;
        this.mapper = mapper;

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

    public ServiceOrder findById(Long id){
        return this.repository.findById(id).orElseThrow(() -> new ServiceOrderNotFoundException("Service Order Not Found !"));
    }

    @Override
    public ServiceOrderResponseDto save(ServiceOrderCreateDto createDto) {
        ServiceOrder serviceOrder = this.mapper.toServiceOrder(createDto);
        return this.mapper.toServiceOrderResponseDto(this.repository.save(serviceOrder));
    }

    @Transactional
    public List<ServiceOrderResponseDto> save(List<ServiceOrderCreateDto> createDtoList){
        List<ServiceOrder> serviceOrderList = this.mapper.toServiceOrderListFromServiceOrderCreateDtoList(createDtoList);
        return this.mapper.toServiceResponseDtoList(this.repository.saveAll(serviceOrderList));
    }

    @Transactional
    public void delete(List<Long> ids){
        this.repository.deleteAllById(ids);
    }

    @Override
    public ServiceOrderResponseDto update(ServiceOrderUpdateDto updateDto) {
        ServiceOrder order = this.mapper.toServiceOrder(updateDto);
        return this.mapper.toServiceOrderResponseDto(this.repository.save(order));
    }

    @Transactional
    public List<ServiceOrderResponseDto> update(List<ServiceOrderUpdateDto> updateDtoList){
        List<ServiceOrder> orders = this.mapper.toServiceOrderListFromServiceOrderUpdateDtoList(updateDtoList);
        return this.mapper.toServiceResponseDtoList(this.repository.saveAll(orders));
    }

    public Integer getEntityCount(){
        return this.repository.getEntityCount();
    }

    public List<ServiceOrderResponseDto> getNotExecuted() {
        List<ServiceOrder> list = this.repository.getNotExecutedList();
        return this.mapper.toServiceResponseDtoList(list);
    }

    public Integer getNotExecutedCount(){
        return this.repository.getNotExecutedCount();
    }
}
