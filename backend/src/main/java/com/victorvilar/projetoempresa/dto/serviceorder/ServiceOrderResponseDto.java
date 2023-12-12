package com.victorvilar.projetoempresa.dto.serviceorder;

import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.domain.Vehicle;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseDto;
import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ServiceOrderResponseDto {


    private Long id;
    private LocalDate emissionDate;
    private LocalDate serviceExpectedDate;
    private Vehicle vehicle;
    private ItemContractResponseDto itemContract;
    private String customerId;
    private String ineaManifest;
    private LocalTime serviceTime;
    private String observation;
    private String osFileUrl;
    private AddressResponseDto address;
    private Long amountCollected;
    private ServiceOrderStatus serviceOrderStatus;

    public ServiceOrderStatus getServiceOrderStatus() {
        return this.serviceOrderStatus;
    }

    public void setServiceOrderStatus(ServiceOrderStatus serviceOrderStatus) {
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

    public ItemContractResponseDto getItemContract() {
        return itemContract;
    }

    public void setItemContract(ItemContractResponseDto itemContract) {
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
}
