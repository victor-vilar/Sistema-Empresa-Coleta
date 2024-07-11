package com.victorvilar.projetoempresa.repository;

import com.victorvilar.projetoempresa.domain.bill.Instalment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstalmentRepository extends JpaRepository<Instalment,Long> {
}

