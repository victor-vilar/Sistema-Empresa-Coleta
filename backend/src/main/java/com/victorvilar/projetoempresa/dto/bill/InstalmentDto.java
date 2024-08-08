package com.victorvilar.projetoempresa.dto.bill;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface InstalmentDto {

    public LocalDate getDueDate();
    public void setDueDate(LocalDate dueDate);
    public LocalDate getPaymentDate();
    public void setPaymentDate(LocalDate paymentDate);
    public BigDecimal getPaymentValue();
    public void setPaymentValue(BigDecimal paymentValue);
    public BigDecimal getPayedValue();
    public void setPayedValue(BigDecimal payedValue);
    public String getPaymentUrl();
    public void setPaymentUrl(String paymentUrl);

}
