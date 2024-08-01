package com.victorvilar.projetoempresa.domain;


import com.victorvilar.projetoempresa.enums.VehicleType;
import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name="vehicles")
public class Vehicle implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(nullable = false)
    private String plate;

    private String renavam;

    private String validCrlvUrl;


    /**
     * ABASTECIMENTOS REALIZADOS
     */
//    @OneToMany
//    List<Refueling> fills = new ArrayList<>();

    /**
    *DESCARTES REALIZADOS
    */
    // @OneToMany
    //List<Tratament> tratamentList = ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getValidCrlvUrl() {
        return validCrlvUrl;
    }

    public void setValidCrlvUrl(String validCrlvUrl) {
        this.validCrlvUrl = validCrlvUrl;
    }
}
