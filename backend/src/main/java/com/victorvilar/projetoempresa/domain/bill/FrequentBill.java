package com.victorvilar.projetoempresa.domain.bill;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Represents a bill of something that is recurrent.
 * It will Save the bills that need to be paid from time to time,
 * so once time a year the system will check all frequent bills
 * that have to be paid and emit a DefaultBill with the same information.
 *
 * If a bill its paid yearly(anual), so it will create a DefaultBill with
 * all the information of a FrequentBill plus month and day of the payment
 *
 * If a FrequentBill it's paid by month(mensal), the system will create 12
 * DefaultBills with the information of a FrequentBill and the day of the payment.
 *
 *
 */
public class FrequentBill extends Bill {

    @Enumerated(EnumType.STRING)
    private BillFrequency billFrequency;



}
