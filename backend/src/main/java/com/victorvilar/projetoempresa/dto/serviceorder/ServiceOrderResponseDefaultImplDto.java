package com.victorvilar.projetoempresa.dto.serviceorder;

import com.victorvilar.projetoempresa.domain.Vehicle;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseImplDto;
import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceOrderResponseDto {


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

    public String getServiceOrderStatus() {
        return ServiceOrderStatus.valueOf(this.serviceOrderStatus).getName();
    }

    public void setServiceOrderStatus(String serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDate emissionDate) {
        this.emissionDate = emissionDate;
    }

    public LocalDate getServiceExpectedDate() {
        return serviceExpectedDate;
    }

    public void setServiceExpectedDate(LocalDate serviceExpectedDate) {
        this.serviceExpectedDate = serviceExpectedDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ItemContractResponseImplDto getItemContract() {
        return itemContract;
    }

    public void setItemContract(ItemContractResponseImplDto itemContract) {
        this.itemContract = itemContract;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getIneaManifest() {
        return ineaManifest;
    }

    public void setIneaManifest(String ineaManifest) {
        this.ineaManifest = ineaManifest;
    }

    public LocalTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getOsFileUrl() {
        return osFileUrl;
    }

    public void setOsFileUrl(String osFileUrl) {
        this.osFileUrl = osFileUrl;
    }

    public AddressResponseDto getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDto address) {
        this.address = address;
    }

    public Long getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(Long amountCollected) {
        this.amountCollected = amountCollected;
    }

    public LocalDate getServiceExecutedDate() {
        return serviceExecutedDate;
    }

    public void setServiceExecutedDate(LocalDate serviceExecutedDate) {
        this.serviceExecutedDate = serviceExecutedDate;
    }
}
