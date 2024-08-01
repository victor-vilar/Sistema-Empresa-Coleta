package com.victorvilar.projetoempresa.exceptions;


public class InvalidCpfOrCnpjException extends RuntimeException  {
	
	public InvalidCpfOrCnpjException(String error) {
		super(error);
	}

}
