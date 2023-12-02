package com.victorvilar.projetoempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorvilar.projetoempresa.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String> {


    public Optional<Customer> findByCpfCnpj(String cpfCnpj);
}
