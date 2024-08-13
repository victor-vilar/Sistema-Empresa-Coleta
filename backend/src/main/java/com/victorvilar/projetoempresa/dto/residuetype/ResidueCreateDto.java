package com.victorvilar.projetoempresa.dto.residuetype;

import com.victorvilar.projetoempresa.dto.residuetype.interfaces.ResidueDto;
import jakarta.validation.constraints.NotNull;

public class ResidueCreateDto implements ResidueDto {

    @NotNull(message = "Residue must have a name")
    private String type;
    private String description;

    public ResidueCreateDto(){

    }

    public ResidueCreateDto(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


