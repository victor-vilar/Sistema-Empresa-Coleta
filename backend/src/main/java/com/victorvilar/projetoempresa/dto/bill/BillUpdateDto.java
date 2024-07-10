package com.victorvilar.projetoempresa.dto.bill;

import com.victorvilar.projetoempresa.domain.bill.Instalment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.MutablePropertyValues;

import java.util.List;

public class BillUpdateDto {

    @NotNull(message = "A bill to update must have an id")
    private Long id;
    @NotNull(message = "A bill to update must have a supplier")
    private String supplier;
    private String noteNumber;
    private List<InstalmentUpdateDto> instalments;
    private String description;

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

    public List<InstalmentUpdateDto> getInstalments() {
        return instalments;
    }

    public void setInstalments(List<InstalmentUpdateDto> instalments) {
        this.instalments = instalments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
