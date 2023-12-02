package com.victorvilar.projetoempresa.exceptions;

/**
 * Exception created to inform that the cpf or cnpj it is not valid
 * @author Victor Vilar
 */
public class InvalidCpfOrCnpjException extends RuntimeException  {
	
	public InvalidCpfOrCnpjException(String error) {
		super(error);
	}

}
