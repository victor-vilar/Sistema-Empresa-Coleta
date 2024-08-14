package com.victorvilar.projetoempresa.services;

import com.victorvilar.projetoempresa.domain.*;
import com.victorvilar.projetoempresa.domain.Contract;
import com.victorvilar.projetoempresa.domain.Customer;
import com.victorvilar.projetoempresa.domain.ItemContract;
import com.victorvilar.projetoempresa.dto.contract.*;
import com.victorvilar.projetoempresa.dto.contract.ItemContractCreateDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractResponseImplDto;
import com.victorvilar.projetoempresa.dto.contract.ItemContractUpdateDto;
import com.victorvilar.projetoempresa.dto.contract.interfaces.ContractResponseDto;
import com.victorvilar.projetoempresa.enums.ContractStatus;
import com.victorvilar.projetoempresa.enums.MeasurementUnit;
import com.victorvilar.projetoempresa.enums.Weekday;
import com.victorvilar.projetoempresa.exceptions.ContractNotFoundException;
import com.victorvilar.projetoempresa.mappers.ContractMapper;
import com.victorvilar.projetoempresa.mappers.ItemContractMapper;
import com.victorvilar.projetoempresa.repository.ContractRepository;
import com.victorvilar.projetoempresa.repository.CustomerRepository;
import com.victorvilar.projetoempresa.repository.ItemContractRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Contract service tests class")
class ContractServiceTest {

    @InjectMocks
    private ContractService contractService;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ItemContractRepository itemContractRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private ContractMapper contractMapper;

    @Mock
    private ItemContractMapper itemContractMapper;

    Contract contract1;
    Contract contract2;

    ContractCreateDto contractCreateDto1;
    ContractCreateDto contractCreateDto2;

    ContractUpdateDto contractUpdateDto1;
    ContractUpdateDto contractUpdateDto2;

    ContractResponseImplDto contractResponseImplDto2;
    ContractResponseImplDto contractResponseImplDto1;

    ItemContract itemContract1;
    ItemContract itemContract2;
    List<ItemContract> itens = new ArrayList<>();

    ItemContractCreateDto itemContractCreateDto1;
    ItemContractCreateDto itemContractCreateDto2;
    List<ItemContractCreateDto> itensCreateDto = new ArrayList<>();

    Customer customer;
    Residue residue;
    Equipment equipment;
    CollectionFrequency collectionFrequency = new CollectionFrequency();


    @BeforeEach
    void setUp() {

        customer = new Customer.CustomerBuilder()
                .cpfCnpj("58141426001")
                .nameCompanyName("teste")
                .build();

        residue = new Residue("residue 1", "residue 1");
        equipment = new Equipment("equipment 1", 10);

        this.setUpContracts();
        this.setUpContractCreate();
        this.setUpContractUpdate();
        this.setUpContractResponse();
        this.setUpItemContract();
        collectionFrequency.setSchedule("SEMANAL");
        collectionFrequency.setDays(Set.of(Weekday.SEGUNDA,Weekday.QUARTA));

    }

    @Test
    @DisplayName("Get all when successfully")
    public void getAll_WhenSuccessfull() {
        when(this.contractRepository.findAll()).thenReturn(List.of(contract1, contract2));
        when(this.contractMapper.toContractResponseDtoList(Mockito.anyList())).thenReturn(List.of(contractResponseImplDto1, contractResponseImplDto1));
        List<ContractResponseDto> list = this.contractService.getAll();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());

    }

    @Test
    @DisplayName("Get all by customer id when successfully")
    public void getAllByCustomer_WhenSuccessfull() {
        when(this.contractRepository.findByCustomerCpfCnpj(anyString())).thenReturn(anyList());
        when(this.contractMapper.toContractResponseDtoList(List.of(contract1, contract2))).thenReturn(List.of(contractResponseImplDto1, contractResponseImplDto2));
        List<ContractResponseDto> list = this.contractService.getAllByCustomerId(customer.getCpfCnpj());
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals("1000", list.get(0).getNumber());
        Assertions.assertEquals("2000", list.get(1).getNumber());
    }

    @Test
    @DisplayName("Get all by customer id when customer not found")
    public void getAllByCustomer_ReturnEmpty_WhenCustomerNotFound() {
        when(contractRepository.findByCustomerCpfCnpj(anyString())).thenReturn(anyList());
        List<ContractResponseDto> list = this.contractService.getAllByCustomerId(customer.getCpfCnpj());
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Get contract by id when successfully")
    public void getById_WhenSuccessfull() {
        when(this.contractRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(contract1));
        when(this.contractMapper.toContractResponseDto(any(Contract.class))).thenReturn(contractResponseImplDto1);

        ContractResponseDto contractResponseImplDto = this.contractService.getById(2L);
        Assertions.assertEquals(contract1.getBeginDate(), contractResponseImplDto.getBeginDate());
        Assertions.assertEquals(contract1.getEndDate(), contractResponseImplDto.getEndDate());

    }

    @Test
    @DisplayName("get by id throws ContractNotFoundException when contract not found")
    public void getById_throwsContractNotFoundException() {
        when(this.contractRepository.findById(1L))
                .thenThrow(new ContractNotFoundException("Contract Not Found !"));
        ContractNotFoundException exception =
                Assertions.assertThrows(ContractNotFoundException.class, () ->
                        this.contractService.getById(1L));
        Assertions.assertEquals(exception.getClass(), ContractNotFoundException.class);
        Assertions.assertEquals("Contract Not Found !", exception.getMessage());
    }

    @Test
    @DisplayName("find by id when successfull")
    public void findByContractId_WhenSuccessfull() {
        when(this.contractRepository.findById(anyLong())).thenReturn(Optional.of(contract1));
        Contract contract = this.contractService.findByContractId(1L);
        Assertions.assertEquals(contract1.getId(), contract.getId());
        Assertions.assertEquals(contract1.getNumber(), contract.getNumber());
    }

    @Test
    @DisplayName("find throws ContractNotFoundException when contract not found")
    public void findByContractId_throwsContractNotFoundException() {
        when(this.contractRepository.findById(1L))
                .thenThrow(new ContractNotFoundException("Contract Not Found !"));
        ContractNotFoundException exception =
                Assertions.assertThrows(ContractNotFoundException.class, () ->
                        this.contractService.findByContractId(1L));
        Assertions.assertEquals(exception.getClass(), ContractNotFoundException.class);
        Assertions.assertEquals("Contract Not Found !", exception.getMessage());
    }

    @Test
    @DisplayName("save successfully when pass a valid contract")
    void save_Successfully_WhenPassAValidContract() {

        when(this.contractMapper.toContract(any(ContractCreateDto.class)))
                .thenReturn(contract1);
        when(this.customerService.findCustomerById(anyString()))
                .thenReturn(customer);
        when(this.itemContractMapper.toItemContractList(anyList()))
                .thenReturn(itens);
        when(this.contractRepository.save(any(Contract.class)))
                .thenReturn(contract1);
        when(this.contractMapper.toContractResponseDto(contract1))
                .thenReturn(contractResponseImplDto1);

        ContractResponseDto contractResponseImplDto = this.contractService.save(contractCreateDto1);

        verify(this.customerRepository, times(1)).save(any(Customer.class));
        Assertions.assertEquals(contract1.getNumber(), contractResponseImplDto.getNumber());


    }

    @Test
    @DisplayName("add new item to contract whe successfull")
    void addNewItemToContract_whenSuccessfull() {
        when(this.itemContractMapper.toItemContract(itemContractCreateDto1))
                .thenReturn(itemContract1);
        when(this.contractRepository.findById(1L))
                .thenReturn(Optional.of(contract1));
        when(this.contractRepository.save(any()))
                .thenReturn(contract1);
        when(this.contractMapper.toContractResponseDto(any(Contract.class)))
                .thenReturn(contractResponseImplDto1);

        ContractResponseDto contractResponseImplDto = this.contractService.addNewItemToContract(1L, itemContractCreateDto1);


        Assertions.assertEquals(contractResponseImplDto1.getNumber(), contractResponseImplDto.getNumber());
        Assertions.assertEquals(contractResponseImplDto1.getItens().size(), contractResponseImplDto.getItens().size());
        verify(itemContractRepository, times(1)).save(any(ItemContract.class));
        verify(contractRepository, times(1)).save(any(Contract.class));
    }

    @Test
    @DisplayName("Delete a item from contract")
    void delete_whenSuccessfull() {
        this.contractService.delete(1L);
        verify(this.contractRepository,times(1)).deleteById(any());
    }

    @Test
    @DisplayName("Delete a item from contract")
    void deleteItemContract_whenSuccessfull() {
        this.contractService.deleteItemContract(Arrays.asList(1L,2L));
        verify(this.itemContractRepository,times(1)).deleteAllById(any());
    }

    @Test
    @DisplayName("Update contract adding new items ")
    void UpdateContract_whenAddingNewItens() {
        List<ItemContract> list = contract1.getItens().stream().toList();
        contractUpdateDto1.setId(1L);

        //passing a contract without items to check
        when(this.contractRepository.findById(anyLong()))
                .thenReturn(Optional.of(contract2));

        when(this.customerService.findCustomerById(anyString()))
                .thenReturn(customer);
        when(this.itemContractMapper.toItemContractList(anyList()))
                .thenReturn(list);
        when(this.contractMapper.toContractResponseDto(any()))
                .thenReturn(contractResponseImplDto1);
        ContractResponseDto contractResponseImplDto = this.contractService.update(contractUpdateDto1);

        verify(this.contractRepository,times(1)).save(any());
        Assertions.assertFalse(contractResponseImplDto.getItens().isEmpty());
        Assertions.assertEquals(contract1.getItens().size(), contractResponseImplDto.getItens().size());
        Assertions.assertEquals(contract1.getItens().get(0).getItemValue(), contractResponseImplDto.getItens().get(0).getItemValue());


    }


    @Test
    @DisplayName("Update contract updating existent items with an id  ")
    void UpdateContract_whenUpdatingExistentItems() {
        List<ItemContract> list = contract1.getItens().stream().toList();
        contractUpdateDto1.setId(1L);
        list.get(0).setId(1L);
        list.get(1).setId(2L);


        when(this.contractRepository.findById(1L))
                .thenReturn(Optional.of(contract1));

        when(this.customerService.findCustomerById(anyString()))
                .thenReturn(customer);

        when(this.itemContractMapper.toItemContractList(anyList()))
                .thenReturn(list);
        when(this.contractMapper.toContractResponseDto(any()))
                .thenReturn(contractResponseImplDto1);

        when(this.itemContractRepository.findById(any()))
                .thenReturn(Optional.of(new ItemContract()));

        ContractResponseDto contractResponseImplDto = this.contractService.update(contractUpdateDto1);
        verify(this.contractRepository,times(1)).save(any());

        Assertions.assertFalse(contractResponseImplDto.getItens().isEmpty());
        Assertions.assertEquals(contract1.getItens().size(), contractResponseImplDto.getItens().size());
        Assertions.assertEquals(contract1.getItens().get(0).getItemValue(), contractResponseImplDto.getItens().get(0).getItemValue());


    }



    @Test
    @DisplayName("Delete a item from contract")
    void UpdateItemContract_Fields_whenSuccessfull() {

    }


    private void setUpContracts(){
        //contract 1
        contract1 = Contract.builder()
                .number("1000")
                .beginDate(LocalDate.of(2023,11,11))
                .endDate(LocalDate.of(2024,11,11))
                .customer(customer)
                .contractStatus(ContractStatus.ATIVO)
                .build();




        //add item to contract 1
        contract1.addNewItem(new ItemContract(residue,equipment,10,new BigDecimal(10),"coleta residuo",12,collectionFrequency, MeasurementUnit.LITROS));
        contract1.addNewItem(new ItemContract(residue,equipment,20,new BigDecimal(20), "coleta residuo",12,collectionFrequency, MeasurementUnit.LITROS));


        //contract 2
        contract2 = Contract.builder()
                .number("2000")
                .beginDate(LocalDate.of(2023,11,11))
                .endDate(LocalDate.of(2024,11,11))
                .customer(customer)
                .contractStatus(ContractStatus.ATIVO)
                .build();

    }
    private void setUpContractCreate(){

        contractCreateDto1 = ContractCreateDto.builder()
                 .number("1000")
                 .beginDate(LocalDate.of(2023,11,11))
                 .endDate(LocalDate.of(2024,11,11))
                 .customer(customer.getCpfCnpj())
                .contractStatus(ContractStatus.ATIVO.getName())
                 .build();

        contractCreateDto1.setItens(
                Arrays.asList(
                new ItemContractCreateDto(residue.getId(),equipment.getId(),10d,new BigDecimal(10d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO),
                new ItemContractCreateDto(residue.getId(),equipment.getId(),20d,new BigDecimal(20d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO)));


         contractCreateDto2 = ContractCreateDto.builder()
                 .number("2000")
                 .beginDate(LocalDate.of(2023,11,11))
                 .endDate(LocalDate.of(2024,11,11))
                 .customer(customer.getCpfCnpj())
                 .contractStatus(ContractStatus.ATIVO.getName())
                 .build();

        contractCreateDto2.setItens(
                Arrays.asList(
                new ItemContractCreateDto(residue.getId(),equipment.getId(),10d,new BigDecimal(10d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO),
                new ItemContractCreateDto(residue.getId(),equipment.getId(),20d,new BigDecimal(20d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO)));


    }
    private void setUpContractUpdate(){

        contractUpdateDto1 = ContractUpdateDto.builder()
                .number("1000")
                .beginDate(LocalDate.of(2023,11,11))
                .endDate(LocalDate.of(2024,11,11))
                .customer(customer.getCpfCnpj())
                .contractStatus(ContractStatus.ATIVO.getName())
                .build();

        contractUpdateDto1.setItens(
                Arrays.asList(
                        new ItemContractUpdateDto(null,residue.getId(),equipment.getId(),10d,new BigDecimal(10d),"coleta residuo",10,collectionFrequency, MeasurementUnit.LITROS),
                        new ItemContractUpdateDto(null,residue.getId(),equipment.getId(),20d,new BigDecimal(20d),"coleta residuo",10,collectionFrequency, MeasurementUnit.LITROS)));


        contractUpdateDto2 = ContractUpdateDto.builder()
                .number("2000")
                .beginDate(LocalDate.of(2023,11,11))
                .endDate(LocalDate.of(2024,11,11))
                .customer(customer.getCpfCnpj())
                .contractStatus(ContractStatus.ATIVO.getName())
                .build();

        contractUpdateDto2.setItens(
                Arrays.asList(
                        new ItemContractUpdateDto(3L,residue.getId(),equipment.getId(),10d,new BigDecimal(10d),"coleta residuo",10,collectionFrequency, MeasurementUnit.LITROS),
                        new ItemContractUpdateDto(4L,residue.getId(),equipment.getId(),20d,new BigDecimal(20d),"coleta residuo",10,collectionFrequency, MeasurementUnit.LITROS)));
    }
    private void setUpContractResponse(){

        contractResponseImplDto1 = ContractResponseImplDto.builder()
                .number("1000")
                .beginDate(LocalDate.of(2023,11,11))
                .endDate(LocalDate.of(2024,11,11))
                .customer(customer.getCpfCnpj())
                .contractStatus(ContractStatus.ATIVO.getName())
                .build();


        contractResponseImplDto1.setItens(
                Arrays.asList(
                        new ItemContractResponseImplDto(1L,residue.getType(),equipment.getEquipmentName(),10d,new BigDecimal(10d),"coleta residuo"),
                        new ItemContractResponseImplDto(2L,residue.getType(),equipment.getEquipmentName(),10d,new BigDecimal(10d),"coleta residuo")));

        contractResponseImplDto2 = ContractResponseImplDto.builder()
                .number("2000")
                .beginDate(LocalDate.of(2023,11,11))
                .endDate(LocalDate.of(2024,11,11))
                .customer(customer.getCpfCnpj())
                .contractStatus(ContractStatus.ATIVO.getName())
                .build();

        contractResponseImplDto2.setItens(
                Arrays.asList(
                        new ItemContractResponseImplDto(1L,residue.getType(),equipment.getEquipmentName(),10d,new BigDecimal(10d),"coleta residuo"),
                        new ItemContractResponseImplDto(2L,residue.getType(),equipment.getEquipmentName(),10d,new BigDecimal(10d),"coleta residuo")));

    }
    private void setUpItemContract(){
        itemContract1 = new ItemContract(residue,equipment,10d,new BigDecimal(10d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO);
        itemContract2 = new ItemContract(residue,equipment,20d,new BigDecimal(20d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO);
        itemContract1.setContract(contract1);
        itemContract2.setContract(contract1);
        itens.addAll(Arrays.asList(itemContract1,itemContract2));

        itemContractCreateDto1 = new ItemContractCreateDto(residue.getId(),equipment.getId(),10d,new BigDecimal(10d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO);
        itemContractCreateDto2 = new ItemContractCreateDto(residue.getId(),equipment.getId(),20d,new BigDecimal(20d),"coleta residuo",12,collectionFrequency,MeasurementUnit.EQUIPAMENTO);

        itens.addAll(Arrays.asList(itemContract1,itemContract2));
    }



}