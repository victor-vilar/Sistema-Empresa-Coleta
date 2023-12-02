package com.victorvilar.projetoempresa.exceptions;

/**
 * Exception created to inform that the given cpf or cnpj length is wrong
 * cpf = 11 , cnpj = 14
 * @author Victor Vilar
 */
public class WrongLengthOfCpfCnpjException extends RuntimeException  {

	public WrongLengthOfCpfCnpjException(String errorMessage) {
		super(errorMessage);
	}
}
