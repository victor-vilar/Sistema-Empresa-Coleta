package com.victorvilar.projetoempresa.dto.serviceorder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderDto;
import com.victorvilar.projetoempresa.dto.serviceorder.interfaces.ServiceOrderRequestDto;
import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceOrderCreateDto implements ServiceOrderRequestDto {


    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message="the order must have a expected service day")
    private LocalDate serviceExpectedDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate serviceExecutedDate;

    private Long vehicle;

    @NotNull(message="The order must have an item from one contract")
    private Long itemContract;

    private String ineaManifest;
    private LocalTime serviceTime;
    private String observation;
    private String osFileUrl;

    @NotNull(message="The order must have an address")
    private Long address;

    private Long amountCollected;



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
    public ServiceOrderStatus getStatus() {
        if(this.serviceExecutedDate != null && this.amountCollected != null){
            return ServiceOrderStatus.DONE;
        }
        return ServiceOrderStatus.UNDONE;
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
    public LocalDate serviceExecutedDate() {
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

    public void setAddress(Long address) {
        this.address = address;
    }

    public void setAmountCollected(Long amountCollected) {
        this.amountCollected = amountCollected;
    }

    public void setIneaManifest(String ineaManifest) {
        this.ineaManifest = ineaManifest;
    }

    public void setItemContract(Long itemContract) {
        this.itemContract = itemContract;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setOsFileUrl(String osFileUrl) {
        this.osFileUrl = osFileUrl;
    }

    public void setServiceExecutedDate(LocalDate serviceExecutedDate) {
        this.serviceExecutedDate = serviceExecutedDate;
    }

    public void setServiceExpectedDate(LocalDate serviceExpectedDate) {
        this.serviceExpectedDate = serviceExpectedDate;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }
}
