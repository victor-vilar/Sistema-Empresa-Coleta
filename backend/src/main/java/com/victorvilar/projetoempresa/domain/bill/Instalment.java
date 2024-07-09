package com.victorvilar.projetoempresa.domain.bill;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="instalments")
public class Instalment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="bill_id")
    private Bill bill;
    @Column(nullable = false)
    private LocalDate dueDate;
    @Column(nullable = false)
    private BigDecimal paymentValue;
    private LocalDate paymentDate;
    private BigDecimal payedValue;
    private String paymentUrl;

    public Instalment() {
    }

    public Instalment(Long id, Bill bill, LocalDate dueDate, BigDecimal paymentValue, LocalDate paymentDate, BigDecimal payedValue, String paymentUrl) {
        this.id = id;
        this.bill = bill;
        this.dueDate = dueDate;
        this.paymentValue = paymentValue;
        this.paymentDate = paymentDate;
        this.payedValue = payedValue;
        this.paymentUrl = paymentUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(BigDecimal paymentValue) {
        this.paymentValue = paymentValue;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
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
