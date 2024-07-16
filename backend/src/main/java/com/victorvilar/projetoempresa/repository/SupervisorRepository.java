package com.victorvilar.projetoempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorvilar.projetoempresa.domain.Supervisor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor,Long> {

    List<Supervisor> findByCustomerCpfCnpj(String clientId);
    @Query("SELECT COUNT(*) from Supervisor")
    public Integer getEntityCount();

}
