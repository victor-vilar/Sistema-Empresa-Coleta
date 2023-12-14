package com.victorvilar.projetoempresa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContractStatus {

    ATIVO(1,"ATIVO"),
    CANCELADO(2,"CANCELADO"),
    RENOVACAO_PENDENTE(3,"RENOVAÇÃO PENDENTE"),
    ENCERRADO(4,"PENDENTE");

    private final int id;
    private final String name;

    ContractStatus(int id, String name){
        this.id =id;
        this.name = name;
    }


    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public static ContractStatus getById(Integer id){
        for(ContractStatus status : values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }

    public static ContractStatus getByName(String name ){
        for(ContractStatus contractStatus : values()){
            if(contractStatus.getName().equals(name)){
                return contractStatus;
            }
        }
        return null;
    }







}
