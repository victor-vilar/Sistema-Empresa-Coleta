package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.dto.contract.ContractCreateDto;
import com.victorvilar.projetoempresa.dto.contract.ContractResponseDto;
import com.victorvilar.projetoempresa.dto.contract.ContractUpdateDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractCreateDto;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.exceptions.ContractNotFoundException;
import com.victorvilar.projetoempresa.domain.Contract;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.exceptions.ItemContractNotFoundException;
import com.victorvilar.projetoempresa.mappers.ContractMapper;
import com.victorvilar.projetoempresa.mappers.ItemContractMapper;
import com.victorvilar.projetoempresa.repository.ContractRepository;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import com.victorvilar.projetoempresa.repository.ItemContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class ContractService {

    private final ContractRepository repository;
    private final ItemContractRepository itemContractRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ContractMapper contractMapper;
    private final ItemContractMapper itemContractMapper;

    @Autowired
    public ContractService (ContractRepository repository,
                            CustomerService customerService,
                            ItemContractRepository itemContractRepository,
                            ContractMapper contractMapper,
                            ItemContractMapper itemContractMapper,
                            CustomerRepository customerRepository){
        this.repository = repository;
        this.customerService = customerService;
        this.itemContractRepository = itemContractRepository;
        this.contractMapper = contractMapper;
        this.itemContractMapper = itemContractMapper;
        this.customerRepository = customerRepository;
    }

    public List<ContractResponseDto> getAll() {

        return this.contractMapper.toContractResponseDtoList(this.repository.findAll());
    }

    public List<ContractResponseDto> getAllByCustomerId(String clientId){
        return this.contractMapper.toContractResponseDtoList(repository.findByCustomerCpfCnpj(clientId));
    }

    public ContractResponseDto getById(Long id) throws ContractNotFoundException{
        Contract contract = this.repository.findById(id).orElseThrow(() -> new ContractNotFoundException("This contract doesn't exist") );
        return this.contractMapper.toContractResponseDto(contract);
    }

    public Contract findByContractId(Long id) throws ContractNotFoundException{
        Contract contract = this.repository.findById(id).orElseThrow(() -> new ContractNotFoundException("This contract doesn't exist") );
        return contract;
    }

    @Transactional
    public ContractResponseDto save(ContractCreateDto contractCreateDto) {

        Contract contract = this.contractMapper.toContract(contractCreateDto);
        Customer customer = this.customerService.findCustomerById(contractCreateDto.getCustomerId());

        List<ItemContract> list =
        this.itemContractMapper.ToItemContractList(contractCreateDto.getItens());

        list.stream().forEach(item -> contract.addNewItem(item));

        customer.addNewContract(contract);
        this.customerRepository.save(customer);
        return this.contractMapper.toContractResponseDto(this.repository.save(contract));
    }

    @Transactional
    public ContractResponseDto addNewItemToContract(Long contractId, ItemContractCreateDto itemDto) {


        ItemContract item = this.itemContractMapper.toItemContract(itemDto);

        Contract contract = this.findByContractId(contractId);

        contract.addNewItem(item);

        itemContractRepository.save(item);

        contract = this.repository.save(contract);

        return this.contractMapper.toContractResponseDto(contract);
    }

    @Transactional
    public void delete(Long contractId ){
        this.repository.deleteById(contractId);
    }

    @Transactional
    public void deleteItemContract(List<Long> itens) {
        this.itemContractRepository.deleteAllById(itens);
    }

    @Transactional
    public ContractResponseDto update(ContractUpdateDto contractUpdateDto){

        //creates instance of contract
        Contract contract = this.findByContractId(contractUpdateDto.getId());

        //updating contract data
        contract.setCustomer(this.customerService.findCustomerById(contractUpdateDto.getCustomerId()));
        contract.setNumber(contractUpdateDto.getNumber());
        contract.setBeginDate(contractUpdateDto.getBeginDate());
        contract.setEndDate(contractUpdateDto.getEndDate());
        contract.setContractStatus(contractUpdateDto.getContractStatus());

        //transform itemContractCreateList into a ItemContractList and add to contract
        List<ItemContract> lista = this.itemContractMapper.fromItemContractUpdateDtoListToItemContractList(contractUpdateDto.getItens());

        //loop to insert a new item or update an exist one
        lista.stream().forEach(item ->{

            //setting contract to item, the itens comes with a null contract from mapper
            item.setContract(contract);

            //if item id it's not null, so its a item update
            if(item.getId() != null){
                updateItemContract(contract,item);

                //else it is a new item
            }else{
                contract.addNewItem(item);
            }

        });

        //saves contract and return as a contract resposne dto
        return this.contractMapper.toContractResponseDto(this.repository.save(contract));
    }

    /**
     * update a item of contract
     * @return
     */
    @Transactional
    public void updateItemContract(Contract contract, ItemContract item){
        ItemContract itemToUpdate = this.itemContractRepository.findById(item.getId()).orElseThrow(() -> new ItemContractNotFoundException("Item not found"));
        itemToUpdate.setEquipment(item.getEquipment());
        itemToUpdate.setResidue(item.getResidue());
        itemToUpdate.setQtdOfResidue(item.getQtdOfResidue());
        itemToUpdate.setItemValue(item.getItemValue());
        itemContractRepository.save(item);
    }

    /**
     * get the total of entitys persisted
     * @return integer of the count
     */
    public Integer getEntityCount(){
        return this.repository.getEntityCount();
    }

}

