package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.Address;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.domain.ServiceOrder;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderCreateDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderUpdateDto;
import com.victorvilar.projetoempresa.exceptions.ServiceOrderNotFoundException;
import com.victorvilar.projetoempresa.mappers.ServiceOrderMapper;
import com.victorvilar.projetoempresa.repository.ItemContractRepository;
import com.victorvilar.projetoempresa.repository.ServiceOrderRepository;
import com.victorvilar.projetoempresa.repository.VehicleRepository;
import com.victorvilar.projetoempresa.services.interfaces.EntityOfCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOrderService implements EntityOfCustomerService<ServiceOrderCreateDto, ServiceOrderUpdateDto, ServiceOrderResponseDto> {


    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderMapper mapper;

    @Autowired
    public ServiceOrderService(ServiceOrderRepository repository,ServiceOrderMapper mapper){
        this.serviceOrderRepository = repository;
        this.mapper = mapper;

    }

    /**
     * get all
     *
     * @return list of service orders
     */
    @Override
    public List<ServiceOrderResponseDto> getAll() {
        return this.mapper.toServiceResponseDtoList(this.serviceOrderRepository.findAll());
    }

    /**
     * get all by customer id
     *
     * @param customerId id of a contract
     * @return a list of service orders
     */
    @Override
    public List<ServiceOrderResponseDto> getAllByCustomerId(String customerId) {
        return this.mapper.toServiceResponseDtoList(this.serviceOrderRepository.findByCustomerCpfCnpj(customerId));
    }

    /**
     * get all by item id
     *
     * @param itemId id of the item
     * @return a list of service orders
     */
    public List<ServiceOrderResponseDto> getAllByItemContract(Long itemId){
        //TODO
        return null;
    }

    /**
     * get by id
     *
     * @param id id of a service order
     * @return service order
     */
    @Override
    public ServiceOrderResponseDto getById(Long id) {
        return this.mapper.toServiceOrderResponseDto(this.serviceOrderRepository.findById(id).orElseThrow(() -> new ServiceOrderNotFoundException("Service Order Not Found !")));
    }

    /**
     * get by id without map to dto
     *
     * @param id id of a service order
     * @return service order
     */
    public ServiceOrder findById(Long id){
        return this.serviceOrderRepository.findById(id).orElseThrow(() -> new ServiceOrderNotFoundException("Service Order Not Found !"));
    }

    /**
     * save a service order
     *
     * @param createDto a service order create dto
     * @return service order
     */
    @Override
    public ServiceOrderResponseDto save(ServiceOrderCreateDto createDto) {
        ServiceOrder serviceOrder = this.mapper.toServiceOrder(createDto);
        return this.mapper.toServiceOrderResponseDto(this.serviceOrderRepository.save(serviceOrder));
    }

    /**
     * save a list of service order
     *
     * @param createDtoList a service order list
     * @return list of service order
     */
    @Transactional
    public List<ServiceOrderResponseDto> save(List<ServiceOrderCreateDto> createDtoList){
        List<ServiceOrder> serviceOrderList = this.mapper.toServiceOrderListFromServiceOrderCreateDtoList(createDtoList);
        return this.mapper.toServiceResponseDtoList(this.serviceOrderRepository.saveAll(serviceOrderList));
    }


    /**
     * delete list of service order
     *
     * @param ids ids of services orders
     */
    @Transactional
    public void delete(List<Long> ids){
        this.serviceOrderRepository.deleteAllById(ids);
    }

    /**
     * update service order
     *
     * @param updateDto service order
     * @return service order
     */
    @Override
    public ServiceOrderResponseDto update(ServiceOrderUpdateDto updateDto) {
        ServiceOrder order = this.mapper.toServiceOrder(updateDto);
        return this.mapper.toServiceOrderResponseDto(this.serviceOrderRepository.save(order));
    }

    /**
     * update a list of itens
     *
     * @param updateDtoList list of service order update dto
     * @return update response dto list
     */
    @Transactional
    public List<ServiceOrderResponseDto> update(List<ServiceOrderUpdateDto> updateDtoList){
        List<ServiceOrder> orders = this.mapper.toServiceOrderListFromServiceOrderUpdateDtoList(updateDtoList);
        return this.mapper.toServiceResponseDtoList(this.serviceOrderRepository.saveAll(orders));
    }



}
