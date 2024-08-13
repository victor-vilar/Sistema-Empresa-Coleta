package com.victorvilar.projetoempresa.dto.equipment;

import com.victorvilar.projetoempresa.dto.equipment.interfaces.EquipmentDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipmentCreateDto implements EquipmentDto {


    @NotBlank(message="The equipment must have a name")
    private String equipmentName;
    @NotNull(message="The equipment must have a size")
    private double sizeInMeterCubic;

    public EquipmentCreateDto() {
    }

    public EquipmentCreateDto( String equipmentName, double sizeInMeterCubic) {
        this.equipmentName = equipmentName;
        this.sizeInMeterCubic = sizeInMeterCubic;
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
