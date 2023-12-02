package com.victorvilar.projetoempresa.business.rules.customer;

import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.exceptions.InvalidCpfOrCnpjException;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import com.victorvilar.projetoempresa.util.CpfCnpjValidator;
import org.springframework.stereotype.Component;

@Component
public class CustomerCpfCnpjNotValid implements CustomerRegisterRuler{

    @Override
    public void verification(Customer customer, CustomerRepository repository) {

        if(!CpfCnpjValidator.checkIfIsValid(customer.getCpfCnpj())) {
            throw new InvalidCpfOrCnpjException("This CPF or CNPJ is Invalid");
        }
    }
}
