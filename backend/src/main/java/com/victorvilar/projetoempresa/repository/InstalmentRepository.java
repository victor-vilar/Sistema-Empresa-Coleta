package com.victorvilar.projetoempresa.repository;

import com.victorvilar.projetoempresa.domain.bill.Instalment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalmentRepository extends JpaRepository<Instalment,Long> {
}

