package com.victorvilar.projetoempresa.enums;

/**
 * Service order status
 */
public enum ServiceOrderStatus {

    UNDONE(0,"EM ABERTO"),
    DONE(1, "FINALIZADO"),
    CANCELLED(2,"CANCELADO");

    private final int id;
    private final String name;

    ServiceOrderStatus(int status, String name){
        this.id = status; this.name = name;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public static ServiceOrderStatus getById(Integer id){
        for(ServiceOrderStatus status : values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }

    public static ServiceOrderStatus getByName(String name){
        for(ServiceOrderStatus s : ServiceOrderStatus.values()){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }



}
