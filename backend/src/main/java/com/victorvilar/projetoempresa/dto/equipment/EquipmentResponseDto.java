package com.victorvilar.projetoempresa.dto.equipment;

import com.victorvilar.projetoempresa.dto.equipment.interfaces.EquipmentDto;

import java.io.Serializable;

public class EquipmentResponseDto implements EquipmentDto, Serializable {


    private Long id;
    private String equipmentName;
    private double sizeInMeterCubic;

    public EquipmentResponseDto() {
    }

    public EquipmentResponseDto(Long id, String equipmentName, double sizeInMeterCubic) {
        this.id = id;
        this.equipmentName = equipmentName;
        this.sizeInMeterCubic = sizeInMeterCubic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public double getSizeInMeterCubic() {
        return sizeInMeterCubic;
    }

    public void setSizeInMeterCubic(double sizeInMeterCubic) {
        this.sizeInMeterCubic = sizeInMeterCubic;
    }
}
