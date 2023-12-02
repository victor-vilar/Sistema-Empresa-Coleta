package com.victorvilar.projetoempresa.domain;


import com.victorvilar.projetoempresa.enums.VechicleType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * REPRESENTAR UM VEICULO -- todo
 */
@Entity
@Table(name="vehicles")
public class Vehicle implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //tipo de Veiculo
    @Enumerated(EnumType.STRING)
    private VechicleType type;

    //placa XXX-XXXX
    @Column(nullable = false)
    private String plate;

    //renavam
    private String renavam;

    //url do ultimo crlv emitido
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

    public VechicleType getType() {
        return type;
    }

    public void setType(VechicleType type) {
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
