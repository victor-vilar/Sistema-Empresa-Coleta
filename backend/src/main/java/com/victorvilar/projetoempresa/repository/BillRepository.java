package com.victorvilar.projetoempresa.repository;

import com.victorvilar.projetoempresa.domain.bill.Bill;
import com.victorvilar.projetoempresa.domain.bill.Instalment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
