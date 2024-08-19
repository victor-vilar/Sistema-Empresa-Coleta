package com.victorvilar.projetoempresa.dto.serviceorder;

import com.victorvilar.projetoempresa.domain.Vehicle;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractResponseDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderResponseDto;
import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceOrderResponseDefaultImplDto implements ServiceOrderResponseDto {


    private Long id;
    private LocalDate emissionDate;
    private LocalDate serviceExpectedDate;
    private LocalDate serviceExecutedDate;
    private Vehicle vehicle;
    private ItemContractResponseImplDto itemContract;
    private String customerId;
    private String ineaManifest;
    private LocalTime serviceTime;
    private String observation;
    private String osFileUrl;
    private AddressResponseDto address;
    private Long amountCollected;
    private String serviceOrderStatus;


    @Override
    public ItemContractResponseDto getItemContract() {
        return this.itemContract;
    }

    @Override
    public String getCustomerId() {
        return this.customerId;
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public AddressResponseDto getAddress() {
        return this.address;
    }

    @Override
    public LocalDate getEmissionDate() {
        return this.emissionDate;
    }

    @Override
    public LocalDate getServiceExpectedDate() {
        return this.serviceExpectedDate;
    }

    @Override
    public LocalDate getServiceExecutedDate() {
        return this.serviceExecutedDate;
    }

    @Override
    public String getObservation() {
        return this.observation;
    }

    @Override
    public LocalTime getServiceTime() {
        return this.serviceTime;
    }

    @Override
    public Long getAmountCollected() {
        return this.amountCollected;
    }

    @Override
    public String getIneaManifest() {
        return this.ineaManifest;
    }

    @Override
    public String getOsFileUrl() {
        return this.osFileUrl;
    }

    public Long getId() {
        return id;
    }

    public String getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmissionDate(LocalDate emissionDate) {
        this.emissionDate = emissionDate;
    }

    public void setServiceExpectedDate(LocalDate serviceExpectedDate) {
        this.serviceExpectedDate = serviceExpectedDate;
    }

    public void setServiceExecutedDate(LocalDate serviceExecutedDate) {
        this.serviceExecutedDate = serviceExecutedDate;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setItemContract(ItemContractResponseImplDto itemContract) {
        this.itemContract = itemContract;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setIneaManifest(String ineaManifest) {
        this.ineaManifest = ineaManifest;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setOsFileUrl(String osFileUrl) {
        this.osFileUrl = osFileUrl;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }

    public void setAmountCollected(Long amountCollected) {
        this.amountCollected = amountCollected;
    }

    public void setServiceOrderStatus(ServiceOrderStatus serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus.getName();
    }
}
