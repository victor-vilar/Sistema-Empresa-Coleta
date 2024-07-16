package com.victorvilar.projetoempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorvilar.projetoempresa.domain.Address;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByCustomerCpfCnpj(String clientId);
    @Query("SELECT COUNT(*) from Address")
    public Integer getEntityCount();
}
