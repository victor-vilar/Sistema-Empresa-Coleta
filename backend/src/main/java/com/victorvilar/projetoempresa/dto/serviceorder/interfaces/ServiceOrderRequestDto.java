package com.victorvilar.projetoempresa.dto.serviceorder.interfaces;

import com.victorvilar.projetoempresa.enums.ServiceOrderStatus;

public interface ServiceOrderRequestDto extends ServiceOrderDto {

    Long getItemContract();
    Long getAddress();
    Long getVehicle();
    ServiceOrderStatus getStatus();
}
