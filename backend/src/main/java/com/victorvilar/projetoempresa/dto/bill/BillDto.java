package com.victorvilar.projetoempresa.dto.bill;

import java.util.List;

public interface BillDto {

    List<? extends InstalmentDto> getInstalments();
}
