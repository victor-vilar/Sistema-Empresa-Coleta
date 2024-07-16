package com.victorvilar.projetoempresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victorvilar.projetoempresa.domain.Residue;
import org.springframework.data.jpa.repository.Query;

public interface ResidueRepository extends JpaRepository<Residue,Long> {

    @Query("SELECT COUNT(*) from Residue")
    public Integer getEntityCount();
}
