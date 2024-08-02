package com.victorvilar.projetoempresa.dto.serviceorder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceOrderUpdateDto implements ServiceOrderDto {


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



    public ServiceOrderStatus getServiceOrderStatus() {
        return serviceOrderStatus;
    }
    public void setServiceOrderStatus(String serviceOrderStatus) {
        this.serviceOrderStatus = ServiceOrderStatus.getByName(serviceOrderStatus);
    }
    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getServiceExpectedDate() {
        return serviceExpectedDate;
    }

    public void setServiceExpectedDate(LocalDate serviceExpectedDate) {
        this.serviceExpectedDate = serviceExpectedDate;
    }
    public LocalDate getServiceExecutedDate() {
        return serviceExecutedDate;
    }

    public void setServiceExecutedDate(LocalDate serviceExecutedDate) {
        this.serviceExecutedDate = serviceExecutedDate;
    }
    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public Long getItemContract() {
        return itemContract;
    }

    public void setItemContract(Long itemContract) {
        this.itemContract = itemContract;
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

    public Long getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(Long amountCollected) {
        this.amountCollected = amountCollected;
    }


}
