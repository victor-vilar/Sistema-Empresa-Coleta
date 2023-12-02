package com.victorvilar.projetoempresa.domain.bill;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

/**
 * contas -- todo
 */
public abstract class Bill implements Serializable {


    private String id;

    //emissor da nota conta
    @Column(nullable = false)
    private BillEmitter supplier;

    private String noteNumber;
    //parcelas da conta
    @OneToMany()
    private List<Stalment> stalmentList;
    //descrição da conta
    private String description;



}
