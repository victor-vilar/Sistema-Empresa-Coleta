package com.victorvilar.projetoempresa.dto.bill;

import java.util.List;

public class BillResponseDto {

    private String supplier;
    private String noteNumber;
    private List<InstalmentResponseDto> instalments;
    private String description;

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

    public List<InstalmentResponseDto> getInstalments() {
        return instalments;
    }

    public void setInstalments(List<InstalmentResponseDto> stalments) {
        this.instalments = stalments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
