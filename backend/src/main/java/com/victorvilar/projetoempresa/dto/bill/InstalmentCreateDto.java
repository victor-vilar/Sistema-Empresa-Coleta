package com.victorvilar.projetoempresa.dto.bill;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstalmentCreateDto {


    @NotNull(message="An instalment must have a dueDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dueDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate paymentDate;
    @NotNull(message="An instalment must have a payment value")
    private BigDecimal paymentValue;
    private BigDecimal payedValue;
    private String paymentUrl;

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(BigDecimal paymentValue) {
        this.paymentValue = paymentValue;
    }

    public BigDecimal getPayedValue() {
        return payedValue;
    }

    public void setPayedValue(BigDecimal payedValue) {
        this.payedValue = payedValue;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}
