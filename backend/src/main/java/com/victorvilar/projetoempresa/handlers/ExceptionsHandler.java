package com.victorvilar.projetoempresa.handlers;

import com.victorvilar.projetoempresa.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AddressNotFoundException.class)
    ErrorHandlerResponse AddressNotFoundExceptionHandler(AddressNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AllNumbersAreTheSameException.class)
    ErrorHandlerResponse AllNumbersAreTheSameExceptionHandler(AllNumbersAreTheSameException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ContractNotFoundException.class)
    ErrorHandlerResponse ContractNotFoundExceptionHandler(ContractNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(CpfOrCnpjAlreadyExistsException.class)
    ErrorHandlerResponse CpfOrCnpjAlreadyExistsExceptionHandler(CpfOrCnpjAlreadyExistsException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(CustomerNotFoundException.class)
    ErrorHandlerResponse CustomerNotFoundExceptionHandler(CustomerNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(EquipmentNotFoundException.class)
    ErrorHandlerResponse EquipmentNotFoundExceptionHandler(EquipmentNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(InvalidCpfOrCnpjException.class)
    ErrorHandlerResponse InvalidCpfOrCnpjExceptionHandler(InvalidCpfOrCnpjException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ItemContractNotFoundException.class)
    ErrorHandlerResponse ItemContractNotFoundExceptionHandler(ItemContractNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ItemNotFoundException.class)
    ErrorHandlerResponse ItemNotFoundExceptionHandler(ItemNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ResidueNotFoundException.class)
    ErrorHandlerResponse ResidueNotFoundExceptionHandler(ResidueNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(SupervisorNotFoundException.class)
    ErrorHandlerResponse SupervisorNotFoundExceptionHandler(SupervisorNotFoundException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(WrongLengthOfCpfCnpjException.class)
    ErrorHandlerResponse WrongLengthOfCpfCnpjExceptionHandler(WrongLengthOfCpfCnpjException exception){
        return new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(ApplicationUserNotFoundException.class)
    ResponseEntity<ErrorHandlerResponse> ApplicationUserNotFoundException(ApplicationUserNotFoundException exception){
        ErrorHandlerResponse error = new ErrorHandlerResponse(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<ErrorHandlerResponse>(error,HttpStatus.NOT_FOUND);

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CredentialsNotFoundException.class)
    ResponseEntity<ErrorHandlerResponse> WrongCredentialsException(CredentialsNotFoundException exception){
        ErrorHandlerResponse error = new ErrorHandlerResponse(exception.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorHandlerResponse>(error,HttpStatus.BAD_REQUEST);

    }






}
