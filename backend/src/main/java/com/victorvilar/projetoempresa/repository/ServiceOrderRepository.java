package com.victorvilar.projetoempresa.repository;

import com.victorvilar.projetoempresa.domain.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    public List<ServiceOrder> findByCustomerCpfCnpj(String customerId);
    @Query("SELECT COUNT(*) from ServiceOrder")
    public Integer getEntityCount();
}
