package com.victorvilar.projetoempresa.domain;

import java.io.Serializable;

import jakarta.persistence.*;


@Entity
@Table(name="address")
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressName;
    private String addressNumber;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
    private boolean requiresCollection;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable=false)
	private Customer customer;

    public Address() {

    }

    public Address(Long id, String addressName, String addressNumber, String complement, String zipCode, String city, String state, Customer customer, boolean requiresCollection) {
        this.addressName = addressName;
        this.addressNumber = addressNumber;
        this.complement = complement;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.customer = customer;
        this.id = id;
        this.requiresCollection = requiresCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return this.addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressNumber() {
        return this.addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public void setClient(Customer customer2) {
		this.customer = customer2;
	}

	public Customer getClient() {
		return this.customer;
	}

    public boolean isRequiresCollection() {
        return requiresCollection;
    }
    public void setRequiresCollection(boolean requiresCollection) {
        this.requiresCollection = requiresCollection;
    }

    public static AddressBuilder builder(){
        return new AddressBuilder();
    }

    public static class AddressBuilder{

        private String addressName;
        private String addressNumber;
        private String complement;
        private String zipCode;
        private String city;
        private String state;
        private boolean requiresCollection;

        public AddressBuilder addressName(String addressName){
            this.addressName = addressName;
            return this;
        }

        public AddressBuilder addressNumber(String addressNumber){
            this.addressNumber = addressNumber;
            return this;
        }

        public AddressBuilder complement(String complement){
            this.complement = complement;
            return this;
        }

        public AddressBuilder zipCode(String zipCode){
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder city(String city){
            this.city = city;
            return this;
        }

        public AddressBuilder state(String state){
            this.state = state;
            return this;
        }

        public AddressBuilder requiresCollection(boolean requiresCollection){
            this.requiresCollection = requiresCollection;
            return this;
        }

        public Address build(){
            Address address= new Address();
            address.setAddressName(this.addressName);
            address.setAddressNumber(this.addressNumber);
            address.setComplement(this.complement);
            address.setZipCode(this.zipCode);
            address.setCity(this.city);
            address.setState(this.state);
            address.setRequiresCollection(this.requiresCollection);
            return address;
        }
    }

}
