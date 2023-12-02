package com.victorvilar.projetoempresa.domain.bill;

import com.victorvilar.projetoempresa.domain.bill.Bill;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Stalment {

    private Long id;
    //conta

    @ManyToOne
    @JoinColumn(name="bill_id")
    private Bill bill;

    //data de vencimento
    private LocalDate dueDate;

    //valor do pagamento
    private BigDecimal paymentValue;

    // data de pagamento
    private LocalDate paymentDate;

    //valor pago
    private BigDecimal payedValue;

    private String paymentUrl;

}
