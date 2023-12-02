package com.victorvilar.projetoempresa.domain.bill;

/**
 *A BillEmiiter it must have a way to get in contact
 * to request a bill copy or
 * a new date to pay the bill or even a login and password
 * to get the bill from a site */

public interface BillEmitter {
    public String getContactInformation();
}
