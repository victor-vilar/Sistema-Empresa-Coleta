package com.victorvilar.projetoempresa.mappers;

import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseDto;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.dto.contract.ItemContractDto;
import com.victorvilar.projetoempresa.services.EquipmentService;
import com.victorvilar.projetoempresa.services.ResidueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemContractMapper {

    private final ModelMapper mapper;
    private final ResidueService residueService;
    private final EquipmentService equipmentService;

    @Autowired
    public ItemContractMapper(ModelMapper mapper,
                              ResidueService residueService,
                              EquipmentService equipmentService){
        this.mapper = mapper;
        this.residueService=residueService;
        this.equipmentService = equipmentService;
    }


    public ItemContract toItemContract(ItemContractDto itemDto){
        ItemContract item = this.mapper.map(itemDto,ItemContract.class);
        item.setResidue(this.residueService.findById(itemDto.getResidue()));
        item.setEquipment(this.equipmentService.findById(itemDto.getEquipment()));

        //set contract to null cause mapper was setting contract id equals item id, throwing an exception
        item.setContract(null);

        return item;
    }

    public ItemContractResponseDto toItemContractResponseDto(ItemContract item){
        return this.mapper.map(item,ItemContractResponseDto.class);
    }

    public List<ItemContract> toItemContractList(List<? extends ItemContractDto> list){
           return list.stream().map(e -> this.toItemContract(e)).collect(Collectors.toList());
    }


}
