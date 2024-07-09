package com.victorvilar.projetoempresa.domain.bill;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * contas -- todo
 */
@Entity
@Table(name="bill")
public  class Bill implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable = false)
    private String supplier;
    private String noteNumber;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instalment> stalments;
    private String description;

    public Bill() {
    }

    public Bill(String id, String supplier, String noteNumber, List<Instalment> stalments, String description) {
        this.id = id;
        this.supplier = supplier;
        this.noteNumber = noteNumber;
        this.stalments = stalments;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Instalment> getStalments() {
        return stalments;
    }

    public void addNewStalment(Instalment stalment) {
        stalment.setBill(this);
        this.stalments.add(stalment);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
