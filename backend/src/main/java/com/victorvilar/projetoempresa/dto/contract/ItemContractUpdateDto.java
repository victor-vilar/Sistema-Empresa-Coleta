package com.victorvilar.projetoempresa.dto.contract;


import com.victorvilar.projetoempresa.domain.CollectionFrequency;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractRequestDto;
import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemContractUpdateDto implements ItemContractRequestDto {

    @NotNull(message="A item to update, must have an id.")
    private Long id;

    @NotNull(message = "The item must have a residue")
    private Long residue;

    @NotNull(message = "the item must have a equipament")
    private Long equipment;

    @NotNull(message="the item must have a quantity of equipment that going to be available")
    private Integer equipmentQuantity;

    @NotNull(message = "the item must have a quantity")
    private Double qtdOfResidue;

    @NotNull(message = "the item must have a value")
    private BigDecimal itemValue;

    private String description;
    private CollectionFrequency collectionFrequency;

    @NotNull(message = "the item must have a unit measurement ")
    private MeasurementUnit measurementUnit;

    public ItemContractUpdateDto() {

    }

    public ItemContractUpdateDto(Long id, Long residue, Long equipment, Double qtdOfResidue, BigDecimal itemValue, String description,Integer equipmentQuantity, CollectionFrequency collectionFrequency, MeasurementUnit measurementUnit) {
        this.id = id;
        this.residue = residue;
        this.equipment = equipment;
        this.qtdOfResidue = qtdOfResidue;
        this.itemValue = itemValue;
        this.description=description;
        this.equipmentQuantity = equipmentQuantity;
        this.collectionFrequency = collectionFrequency;
        this.measurementUnit = measurementUnit;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResidue() {
        return residue;
    }

    public void setResidue(Long residue) {
        this.residue = residue;
    }

    public Long getEquipment() {
        return equipment;
    }

    public void setEquipment(Long equipament) {
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

    public String getDescription() {return this.description;}

    public void setDescription(String description){this.description = description;}

    public Integer getEquipmentQuantity() {return this.equipmentQuantity;}

    public void setEquipmentQuantity(Integer equipmentQuantity){this.equipmentQuantity = equipmentQuantity;}

    public CollectionFrequency getCollectionFrequency(){ return this.collectionFrequency;}

    public void setCollectionFrequency(CollectionFrequency collectionFrequency){this.collectionFrequency = collectionFrequency;}

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = MeasurementUnit.getByName(measurementUnit);
    }

}
