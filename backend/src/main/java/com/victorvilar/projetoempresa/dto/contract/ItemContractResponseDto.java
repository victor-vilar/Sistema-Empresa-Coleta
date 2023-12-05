package com.victorvilar.projetoempresa.dto.contract;

import com.victorvilar.projetoempresa.domain.CollectionFrequency;
import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemContractResponseDto {

    private Long id;
    private String residue;
    private String equipment;
    private Integer equipmentQuantity;
    private Double qtdOfResidue;
    private BigDecimal itemValue;
    private String contract;
    private String description;
    private CollectionFrequency collectionFrequency;
    private MeasurementUnit measurementUnit;

    public ItemContractResponseDto() {
    }

    public ItemContractResponseDto(Long id, String residue, String equipment, Double qtdOfResidue, BigDecimal itemValue, String description) {
        this.id = id;
        this.residue = residue;
        this.equipment = equipment;
        this.qtdOfResidue = qtdOfResidue;
        this.itemValue = itemValue;
        this.description =description;
    }

    public String getContract() {
        return contract;
    }
    public void setContract(String contract) {
        this.contract = contract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResidue() {
        return residue;
    }

    public void setResidue(String residue) {
        this.residue = residue;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipament) {
        this.equipment = equipament;
    }

    public Double getQtdOfResidue() {
        return qtdOfResidue;
    }

    public void setQtdOfResidue(Double qtdOfResidue) {
        this.qtdOfResidue = qtdOfResidue;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }

    //getters and setters - description
    public String getDescription() {return this.description;}
    public void setDescription(String description){this.description = description;}
    //-----------

    //getters and setters - equipment quantity
    public Integer getEquipmentQuantity() {return this.equipmentQuantity;}
    public void setEquipmentQuantity(Integer equipmentQuantity){this.equipmentQuantity = equipmentQuantity;}
    //-----------

    //getters and setter - collection frequency
    public CollectionFrequency getCollectionFrequency(){ return this.collectionFrequency;}
    public void setCollectionFrequency(CollectionFrequency collectionFrequency){this.collectionFrequency = collectionFrequency;}
    //-----------

    //getters and setter - measurement unit
    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }
    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
    //-----------

}
