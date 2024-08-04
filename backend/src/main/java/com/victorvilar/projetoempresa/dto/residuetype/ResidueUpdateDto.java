package com.victorvilar.projetoempresa.dto.residuetype;

import jakarta.validation.constraints.NotNull;

public class ResidueUpdateDto implements ResidueDto {
    @NotNull(message = "A residue to update must have an id")
    private Long id;
    @NotNull(message = "Residue must have a name")
    private String type;
    private String description;

    public ResidueUpdateDto(){

    }

    public ResidueUpdateDto(Long id,String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
