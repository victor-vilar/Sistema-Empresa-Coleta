package com.victorvilar.projetoempresa.enums;

/**
 * Service order status
 */
public enum ServiceOrderStatus {

    UNDONE(0),
    DONE(1),
    CANCELLED(2);

    private final int id;

    ServiceOrderStatus(int status){
        this.id = status;
    }

    public int getId(){
        return this.id;
    }

    public static ServiceOrderStatus getById(Integer id){
        for(ServiceOrderStatus status : values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }

}
