package com.victorvilar.projetoempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorvilar.projetoempresa.domain.Supervisor;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor,Long> {

    List<Supervisor> findByCustomerCpfCnpj(String clientId);

}
