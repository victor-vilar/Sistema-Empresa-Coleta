package com.victorvilar.projetoempresa.dto.adress;

import com.victorvilar.projetoempresa.dto.adress.interfaces.AddressDto;
import jakarta.validation.constraints.NotBlank;

public class AddressCreateDto implements AddressDto {


    private String addressName;
    private String addressNumber;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
    private boolean requiresCollection;
    @NotBlank(message="An address must have a customer")
    private String customerId;


    public AddressCreateDto() {

    }

    public AddressCreateDto(
                            String addressName,
                            String addressNumber,
                            String complement,
                            String zipCode,
                            String city,
                            String state,
                            boolean requiresCollection,
                            String customer) {

        this.addressName = addressName;
        this.addressNumber = addressNumber;
        this.complement = complement;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.requiresCollection = requiresCollection;
        this.customerId = customer;
    }


    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressNumber() {
        return addressNumber;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isRequiresCollection() {
        return requiresCollection;
    }

    public void setRequiresCollection(boolean requiresCollection) {
        this.requiresCollection = requiresCollection;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public static AddressCreateBuilder builder(){
        return new AddressCreateBuilder();
    }

    public static class AddressCreateBuilder{

        private String addressName;
        private String addressNumber;
        private String complement;
        private String zipCode;
        private String city;
        private String state;
        private boolean requiresCollection;
        private String customerId;

        public AddressCreateBuilder addressName(String addressName){
            this.addressName = addressName;
            return this;
        }

        public AddressCreateBuilder addressNumber(String addressNumber){
            this.addressNumber = addressNumber;
            return this;
        }

        public AddressCreateBuilder complement(String complement){
            this.complement = complement;
            return this;
        }

        public AddressCreateBuilder zipCode(String zipCode){
            this.zipCode = zipCode;
            return this;
        }

        public AddressCreateBuilder city(String city){
            this.city = city;
            return this;
        }

        public AddressCreateBuilder state(String state){
            this.state = state;
            return this;
        }

        public AddressCreateBuilder requiresCollection(boolean requiresCollection){
            this.requiresCollection = requiresCollection;
            return this;
        }

        public AddressCreateBuilder customerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public AddressCreateDto build(){
            AddressCreateDto address= new AddressCreateDto();
            address.setAddressName(this.addressName);
            address.setAddressNumber(this.addressNumber);
            address.setComplement(this.complement);
            address.setZipCode(this.zipCode);
            address.setCity(this.city);
            address.setState(this.state);
            address.setRequiresCollection(this.requiresCollection);
            address.setCustomerId(this.customerId);
            return address;
        }
    }


}
