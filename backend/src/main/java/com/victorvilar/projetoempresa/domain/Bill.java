package com.victorvilar.projetoempresa.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * contas -- todo
 */
@Entity
@Table(name="bill")
public  class Bill implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String supplier;
    private String noteNumber;
    private String description;
    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instalment> instalments = new ArrayList<>();


    public Bill() {
    }

    public Bill(Long id, String supplier, String noteNumber, List<Instalment> stalments, String description) {
        this.id = id;
        this.supplier = supplier;
        this.noteNumber = noteNumber;
        this.instalments = stalments;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(String noteNumber) {
        this.noteNumber = noteNumber;
    }

    public List<Instalment> getInstalments() {
        return instalments;
    }

    public void addNewInstalment(Instalment instalment) {
        if(!this.instalments.contains(instalment)){
            instalment.setBill(this);
            this.instalments.add(instalment);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
