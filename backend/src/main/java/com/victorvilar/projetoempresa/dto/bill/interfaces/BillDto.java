package com.victorvilar.projetoempresa.dto.bill.interfaces;

import java.util.List;

public interface BillDto {
    String getSupplier();
    void setSupplier(String supplier);
    String getNoteNumber();
    void setNoteNumber(String noteNumber);
    String getDescription();
    void setDescription(String description);
    List<? extends InstalmentDto> getInstalments();
}
