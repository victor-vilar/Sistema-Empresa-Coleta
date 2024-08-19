package com.victorvilar.projetoempresa.dto.serviceorder.interfaces;

import com.victorvilar.projetoempresa.domain.Vehicle;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ItemContractResponseDto;

public interface ServiceOrderResponseDto extends ServiceOrderDto{

    ItemContractResponseDto getItemContract();
    String getCustomerId();
    Vehicle getVehicle();
    AddressResponseDto getAddress();
}
