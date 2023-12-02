package com.victorvilar.projetoempresa.business.rules.customer;

import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.exceptions.CpfOrCnpjAlreadyExistsException;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerAlreadyExistRuler implements  CustomerRegisterRuler{

    @Override
    public void verification(Customer customer, CustomerRepository repository) {
        boolean isPresent = repository.findByCpfCnpj(customer.getCpfCnpj()).isPresent();
        if(isPresent){
            throw new CpfOrCnpjAlreadyExistsException("This Cpf/Cnpj already exists");
        }
    }
}
