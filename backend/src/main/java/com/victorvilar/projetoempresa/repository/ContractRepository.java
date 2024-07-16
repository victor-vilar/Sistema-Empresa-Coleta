package com.victorvilar.projetoempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorvilar.projetoempresa.domain.Contract;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {

    List<Contract> findByCustomerCpfCnpj(String clientId);
    @Query("SELECT COUNT(*) from Contract")
    public Integer getEntityCount();
}
