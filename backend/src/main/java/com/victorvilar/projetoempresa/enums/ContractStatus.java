package com.victorvilar.projetoempresa.enums;

public enum ContractStatus {

    ATIVO(1),
    CANCELADO(2),
    RENOVACAO_PENDENTE(3),
    ENCERRADO(4);

    private final int id;

    ContractStatus(int id){
        this.id =id;
    }


    public int getId(){
        return this.id;
    }

    public static ContractStatus getById(Integer id){
        for(ContractStatus status : values()){
            if(status.getId() == id){
                return status;
            }
        }
        return null;
    }

}
