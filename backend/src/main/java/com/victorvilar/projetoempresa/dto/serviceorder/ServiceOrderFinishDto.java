package com.victorvilar.projetoempresa.dto.serviceorder;

import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * service order finish dto to make the service order as done
 */
public class ServiceOrderFinishDto {

    @NotNull(message="The service order must have an id")
    private Long id;
    private String ineaManifest;
    private Long vehicle;
    private LocalTime serviceTime;
    private LocalDate serviceExecutedDate;
    private String osFileUrl;
    @NotNull(message="The service order must have an amount Collected")
    private Long amountCollected;
    private ServiceOrderStatus serviceOrderStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIneaManifest() {
        return ineaManifest;
    }

    public void setIneaManifest(String ineaManifest) {
        this.ineaManifest = ineaManifest;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public LocalTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
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

    public ServiceOrderStatus getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setServiceOrderStatus(ServiceOrderStatus serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }

    public LocalDate getServiceExecutedDate() {
        return serviceExecutedDate;
    }

    public void setServiceExecutedDate(LocalDate serviceExecutedDate) {
        this.serviceExecutedDate = serviceExecutedDate;
    }
}
