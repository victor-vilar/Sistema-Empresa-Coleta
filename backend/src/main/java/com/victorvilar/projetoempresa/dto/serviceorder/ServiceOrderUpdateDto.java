package com.victorvilar.projetoempresa.dto.serviceorder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderRequestDto;
import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceOrderUpdateDto implements ServiceOrderRequestDto {


    @NotNull(message="A service order to update must have an id")
    private Long id;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate serviceExpectedDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate serviceExecutedDate;
    private LocalTime serviceTime;
    @NotNull(message="A service order must have an item")
    private Long itemContract;
    @NotNull(message="The order must have an address")
    private Long address;
    private Long amountCollected;
    private Long vehicle;
    private String ineaManifest;
    private String observation;
    private String osFileUrl;
    private ServiceOrderStatus serviceOrderStatus;

    public Long getId(){
        return this.id;
    }

    @Override
    public Long getItemContract() {
        return this.itemContract;
    }

    @Override
    public Long getAddress() {
        return this.address;
    }

    @Override
    public Long getVehicle() {
        return this.vehicle;
    }


    @Override
    public LocalDate getEmissionDate() {
        return null;
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

    @Override
    public ServiceOrderStatus getStatus() {
        return this.serviceOrderStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceExpectedDate(LocalDate serviceExpectedDate) {
        this.serviceExpectedDate = serviceExpectedDate;
    }

    public void setServiceExecutedDate(LocalDate serviceExecutedDate) {
        this.serviceExecutedDate = serviceExecutedDate;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setItemContract(Long itemContract) {
        this.itemContract = itemContract;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public void setAmountCollected(Long amountCollected) {
        this.amountCollected = amountCollected;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public void setIneaManifest(String ineaManifest) {
        this.ineaManifest = ineaManifest;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setOsFileUrl(String osFileUrl) {
        this.osFileUrl = osFileUrl;
    }

    public void setServiceOrderStatus(Integer serviceOrderStatus) {
        this.serviceOrderStatus = ServiceOrderStatus.getById(serviceOrderStatus);
    }
}
