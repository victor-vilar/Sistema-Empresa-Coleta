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


@Entity
@Table(name="customers")
public class Customer implements Serializable {

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
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Contract> contracts = new ArrayList<Contract>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<ServiceOrder> serviceOrders = new ArrayList<>();


	public Customer() {

	}
	public Customer(String cpfCnpj,String nameCompanyName) {
		this.cpfCnpj = cpfCnpj;
		this.nameCompanyName = nameCompanyName;
	}

	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}


	public String getNameCompanyName() {
		return nameCompanyName;
	}

	public void setNameCompanyName(String nameCompanyName) {
		this.nameCompanyName = nameCompanyName;
	}

	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean active) {
		isActive = active;
	}


	public void addNewAddress(Address address) {
		address.setCustomer(this);
		this.getAddresses().add(address);
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void addNewSupervisor(Supervisor supervisor) {
		supervisor.setCustomer(this);
		this.getSupervisors().add(supervisor);
	}

	public List<Supervisor> getSupervisors() {
		return this.supervisors;
	}

	public void addNewContract(Contract contract) {
		contract.setCustomer(this);
		this.getContracts().add(contract);
	}

	public List<Contract> getContracts() {
		return this.contracts;
	}

	public void addNewServiceOrder(ServiceOrder serviceorder){
		serviceorder.setCustomer(this);
		this.getServiceOrders().add(serviceorder);
	}

	public List<ServiceOrder> getServiceOrders(){
		return this.serviceOrders;
	}

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

	public static CustomerBuilder builer(){
		return new CustomerBuilder();
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
