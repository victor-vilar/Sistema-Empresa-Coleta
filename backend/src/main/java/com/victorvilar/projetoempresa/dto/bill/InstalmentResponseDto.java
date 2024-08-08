package com.victorvilar.projetoempresa.dto.bill;

import com.victorvilar.projetoempresa.dto.bill.interfaces.InstalmentDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstalmentResponseDto implements InstalmentDto {

    private Long id;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal paymentValue;
    private BigDecimal payedValue;
    private String paymentUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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
