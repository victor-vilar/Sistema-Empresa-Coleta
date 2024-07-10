package com.victorvilar.projetoempresa.exceptions;

public class BillNotFoundException extends RuntimeException{

    public BillNotFoundException(String msg){
        super(msg);
    }
}
