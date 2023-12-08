package com.victorvilar.projetoempresa.enums;

/**
 * Service order status
 */
public enum ServiceOrderStatus {

    UNDONE(0),
    DONE(1),
    CANCELED(2);

    private final int status;

    ServiceOrderStatus(int status){
        this.status = status;
    }

    int getStatus(){
        return this.status;
    }

}
