package com.victorvilar.projetoempresa.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String msg){
        super(msg);
    }

}
