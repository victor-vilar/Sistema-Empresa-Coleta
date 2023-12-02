package com.victorvilar.projetoempresa.exceptions;

public class ContractNotFoundException extends RuntimeException {

    public ContractNotFoundException (String msg){
        super(msg);
    }
}
