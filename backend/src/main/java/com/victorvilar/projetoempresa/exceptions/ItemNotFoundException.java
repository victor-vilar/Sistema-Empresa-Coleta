package com.victorvilar.projetoempresa.exceptions;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String msg){
        super(msg);
    }
}
