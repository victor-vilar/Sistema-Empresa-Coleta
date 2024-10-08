package com.victorvilar.projetoempresa.configuration;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.dto.adress.AddressCreateDto;
import com.victorvilar.projetoempresa.dto.adress.AddressResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ContractCreateDto;
import com.victorvilar.projetoempresa.dto.contract.ContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.serviceorder.ServiceOrderResponseDefaultImplDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorResponseDto;
import com.victorvilar.projetoempresa.dto.supervisor.SupervisorCreateDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(Supervisor.class, SupervisorResponseDto.class).
                addMappings(maper -> {
                    maper.map(src -> src.getCustomer().getCpfCnpj(),SupervisorResponseDto::setCustomerId);
                });
        mapper.typeMap(SupervisorCreateDto.class, Supervisor.class)
                .addMappings(maper -> {
                    maper.skip((supervisor, o) -> supervisor.getId());
                });

        mapper.typeMap(Contract.class, ContractResponseImplDto.class)
                .addMappings(maper ->{
                    maper.map(src -> src.getCustomer().getCpfCnpj(), ContractResponseImplDto::setCustomerId);
                });

        mapper.typeMap(ContractCreateDto.class, Contract.class)
                .addMappings(maper -> {
                    maper.skip((contract, o) -> contract.getId());
                });

        mapper.typeMap(Address.class, AddressResponseDto.class)
                .addMappings(maper ->{
                    maper.map(src -> src.getCustomer().getCpfCnpj(), AddressResponseDto::setCustomerId);
                });

        mapper.typeMap(AddressCreateDto.class, Address.class)
                .addMappings(maper -> {
                    maper.skip((address, o) -> address.getId());
                });


        mapper.typeMap(ItemContract.class, ItemContractResponseImplDto.class)
                .addMappings(maper ->{
                    maper.map(src-> src.getResidue().getType(), ItemContractResponseImplDto::setResidue);
                    maper.map(src-> src.getEquipment().getEquipmentName(), ItemContractResponseImplDto::setEquipment);
                    maper.map(src-> src.getContract().getNumber(), ItemContractResponseImplDto::setContract);
                });

        mapper.typeMap(ServiceOrder.class, ServiceOrderResponseDefaultImplDto.class)
                .addMappings(maper ->{
                    maper.map(src-> src.getCustomer().getCpfCnpj(), ServiceOrderResponseDefaultImplDto::setCustomerId);

                });

        return mapper;
    }
}
