package com.victorvilar.projetoempresa.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * class that represents a supervisor of a client.
 * @author Victor Vilar
 * @since 2022-02-01
 *
 */

@Entity
@Table(name="supervisors")
public class Supervisor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	private String role;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable=false)
	private Customer customer;
	
	//constructors
	public Supervisor() {
		
	}
	//-----------

	//getters e setters - id
	public Long getId() {
		return id;
	}
	
	//getters e setters - name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//-----------
	
	//getters e setters - role
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	//-------------
	
	
	//getters e setters = phoneNumber
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	//--------------
	
	
	//getters e setters - email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//--------------

	//getters e setters - client
	public void setCustomer(Customer customer2) {
		this.customer = customer2;
		
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	//--------------
	
	public static SupervisorBuilder builder(){
		return new SupervisorBuilder();
	}
	
	public static class SupervisorBuilder {

		private String name;
		private String role;
		private String phoneNumber;
		private String email;
		private String customerId;
		private Customer customer;

		public SupervisorBuilder name(String name){
			this.name =name;
			return this;
		}

		public SupervisorBuilder role(String role){
			this.role = role;
			return this;
		}

		public SupervisorBuilder phoneNumber(String phoneNumber){
			this.phoneNumber = phoneNumber;
			return this;
		}

		public SupervisorBuilder email(String email){
			this.email = email;
			return this;
		}

		public SupervisorBuilder customer(Customer customer){
			this.customer = customer;
			return this;
		}

		public Supervisor build(){
			Supervisor supervisor = new Supervisor();
			supervisor.setName(this.name);
			supervisor.setRole(this.role);
			supervisor.setPhoneNumber(this.phoneNumber);
			supervisor.setEmail(this.email);
			supervisor.setCustomer(this.customer);
			return supervisor;
		}
	}
	
	
}
