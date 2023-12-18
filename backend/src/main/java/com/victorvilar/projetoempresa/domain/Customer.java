package com.victorvilar.projetoempresa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * 
 * @author Victor Vilar
 * @since 2022-01-31
 * Class that represents a customer
 */

@Entity
@Table(name="clients")
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false,unique = true)
	private String cpfCnpj;

	@Column(nullable=false)
	private String nameCompanyName;

	private boolean isActive = true;



	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<Address>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Supervisor> supervisors = new ArrayList<Supervisor>();
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Contract> contracts = new ArrayList<Contract>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<ServiceOrder> serviceOrders = new ArrayList<>();
	

	/**
	 * Add a new address to client, add this to client of client
	 * @param address
	 */
	public void addNewAddress(Address address) {
		address.setClient(this);
		this.getAddresses().add(address);
	}
	
	/**
	 * Add a new Supervisor to client, add this to supervisor client
	 * @param supervisor
	 */
	public void addNewSupervisor(Supervisor supervisor) {
		supervisor.setCustomer(this);
		this.getSupervisors().add(supervisor);
	}

	/**
	 * Add a new Contract to client, add this to contract client
	 * @param contract
	 */
	public void addNewContract(Contract contract) {
		contract.setCustomer(this);
		this.getContracts().add(contract);
	}

	/**
	 * Add a new ServiceOrder to client, add this to contract client
	 * @param serviceorder
	 */
	public void addNewServiceOrder(ServiceOrder serviceorder){
		serviceorder.setCustomer(this);
		this.getServiceOrders().add(serviceorder);
	}


	//Constructors
	public Customer() {
		
	}
	public Customer(String cpfCnpj,String nameCompanyName) {
		this.cpfCnpj = cpfCnpj;
		this.nameCompanyName = nameCompanyName;
	}
	//-------------------------------------
	
	
	//getters and setters - cpfCnpj
	public String getCpfCnpj() {
		return this.cpfCnpj;
	}
	
	/**
	 * Set the cpfCnpj variable. As there are rules to know if the
	 * value it's true, it uses a class to test if the string is correct.
	 * @param cpfCnpj variable that represents a cpf or cnpj number
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	//-------------------------------------

	//getters and setters - Addresses
	public List<Address> getAddresses() {
		return this.addresses;
	}
	//-------------------------------------

	//getters and setters - Supervisors
	public List<Supervisor> getSupervisors() {
		return this.supervisors;
	}
	//-------------------------------------

	//getters and setters - Contracts
	public List<Contract> getContracts() {
		return this.contracts;
	}
	//-------------------------------------

	//getters and setters - ServiceOrders
	public List<ServiceOrder> getServiceOrders(){return this.serviceOrders;}
	//--------------------------------------

	@Override
	public String toString() {
		return "Client{" +

				", nameCompanyName='" + nameCompanyName + '\'' +
				", cpfCnpj='" + cpfCnpj + '\'' +
				", addresses=" + addresses +
				", supervisors=" + supervisors +
				", contracts=" + contracts +
				'}';
	}

	//getters and setter - nameCompanyName
	public String getNameCompanyName() {
		return nameCompanyName;
	}
	public void setNameCompanyName(String nameCompanyName) {
		this.nameCompanyName = nameCompanyName;
	}


	//getters and setters - isActive
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean active) {
		isActive = active;
	}



	public static class CustomerBuilder{

		private String cpfCnpj;
		private String nameCompanyName;
		private boolean isActive;

		public CustomerBuilder(){

		}

		public CustomerBuilder cpfCnpj(String cpfCnpj){
			this.cpfCnpj = cpfCnpj;
			return this;
		}

		public CustomerBuilder nameCompanyName(String nameCompanyName){
			this.nameCompanyName = nameCompanyName;
			return this;
		}

		public CustomerBuilder isActive(boolean isActive){
			this.isActive = isActive;
			return this;

		}

		public Customer build(){
			Customer customer = new Customer();
			customer.setCpfCnpj(this.cpfCnpj);
			customer.setNameCompanyName(this.nameCompanyName);
			customer.setActive(this.isActive);
			return customer;
		}


	}




}
