package com.victorvilar.projetoempresa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.victorvilar.projetoempresa.enums.ContractStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="contracts")
public class Contract implements Serializable {


	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false)
    private String number;

	@Column(nullable=false)
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate beginDate;

	@Column(nullable=false)
	@JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate endDate;
    
    @OneToMany(mappedBy = "contract",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemContract> itens = new ArrayList<ItemContract>();
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable=false)
	private Customer customer;

	private Integer status = 1;
      
	

	public void addNewItem(ItemContract itemContract) {
		if (!itens.contains(itemContract)) {
			itemContract.setContract(this);
			this.itens.add(itemContract);
		}
	}

    //Constructors
    public Contract() {
		
	}

	public Contract(String number, LocalDate beginDate, LocalDate endDate, Customer customer) {
		this.number = number;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<ItemContract> getItens() {
		return itens;
	}

	public void setCustomer(Customer customer2) {
		this.customer = customer2;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void deleteItem(int itemIndex){
		this.itens.remove(itemIndex);
	}

	public void setContractStatus(ContractStatus status){
		if(status == null) return;
		this.status = status.getId();
	}

	public ContractStatus getContractStatus(){
		return ContractStatus.getById(this.status);
	}

	public static ContractBuilder builder(){
		return new ContractBuilder();
	}

	public static final class ContractBuilder{

		private String number;
		private LocalDate beginDate;
		private LocalDate endDate;
		private List<ItemContract> itens = new ArrayList<ItemContract>();
		private Customer customer;
		private ContractStatus contractStatus;

		public ContractBuilder number(String number){
			this.number = number;
			return this;
		}

		public ContractBuilder beginDate(LocalDate beginDate){
			this.beginDate = beginDate;
			return this;
		}

		public ContractBuilder endDate(LocalDate endDate){
			this.endDate = endDate;
			return this;
		}

		public ContractBuilder customer(Customer customer){
			this.customer = customer;
			return this;
		}

		public ContractBuilder contractStatus(ContractStatus contractStatus){
			this.contractStatus = contractStatus;
			return this;
		}


		public Contract build(){
			Contract contract = new Contract();
			contract.setNumber(this.number);
			contract.setBeginDate(this.beginDate);
			contract.setEndDate(this.endDate);
			contract.setCustomer(this.customer);
			contract.setContractStatus(this.contractStatus);
			return contract;
		}

	}


}
