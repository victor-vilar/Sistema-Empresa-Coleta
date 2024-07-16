package com.victorvilar.projetoempresa.repository;

import com.victorvilar.projetoempresa.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    @Query("SELECT COUNT(*) from Vehicle")
    public Integer getEntityCount();
}
