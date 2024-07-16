package com.victorvilar.projetoempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorvilar.projetoempresa.domain.Customer;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String> {


    public Optional<Customer> findByCpfCnpj(String cpfCnpj);

    @Query("SELECT COUNT(*) from Customer")
    public Integer getEntityCount();
}
