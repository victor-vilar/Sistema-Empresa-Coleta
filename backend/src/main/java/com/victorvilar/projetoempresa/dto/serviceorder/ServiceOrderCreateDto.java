package com.victorvilar.projetoempresa.dto.serviceorder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.domain.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ServiceOrderCreateDto {


    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message="the order must have a expected service day")
    private LocalDate serviceExpectedDate;
    @NotNull(message="The order must have an item from one contract")
    private Long itemContract;
    @NotNull(message="The order must have an address")
    private Long address;

    private String observation;

    public LocalDate getServiceExpectedDate() {
        return serviceExpectedDate;
    }
    public void setServiceExpectedDate(LocalDate serviceExpectedDate) {
        this.serviceExpectedDate = serviceExpectedDate;
    }
    public Long getItemContract() {
        return itemContract;
    }

    public void setItemContract(Long itemContract) {
        this.itemContract = itemContract;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
