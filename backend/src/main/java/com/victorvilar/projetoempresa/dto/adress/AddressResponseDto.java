package com.victorvilar.projetoempresa.dto.adress;

public class AddressResponseDto implements AddressDto {

    private Long id;
    private String addressName;
    private String addressNumber;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
    private String customer;
    private boolean requiresCollection;

    public AddressResponseDto() {
    }

    public AddressResponseDto(Long id,String addressName, String addressNumber, String complement, String zipCode, String city, String state, String clientId, boolean requiresCollection) {
        this.addressName = addressName;
        this.addressNumber = addressNumber;
        this.complement = complement;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.customer = clientId;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String clientId) {
        this.customer = clientId;
    }

    public boolean isRequiresCollection() {
        return requiresCollection;
    }

    public void setRequiresCollection(boolean requiresCollection) {
        this.requiresCollection = requiresCollection;
    }


    public static AddressResponseDtoBuilder builder(){
        return new AddressResponseDtoBuilder();
    }
    
    public static class AddressResponseDtoBuilder{

        private Long id;
        private String addressName;
        private String addressNumber;
        private String complement;
        private String zipCode;
        private String city;
        private String state;
        private boolean requiresCollection;
        private String customerId;

        public AddressResponseDtoBuilder id(Long id){
            this.id = id;
            return this;
        }

        public AddressResponseDtoBuilder addressName(String addressName){
            this.addressName = addressName;
            return this;
        }

        public AddressResponseDtoBuilder addressNumber(String addressNumber){
            this.addressNumber = addressNumber;
            return this;
        }

        public AddressResponseDtoBuilder complement(String complement){
            this.complement = complement;
            return this;
        }

        public AddressResponseDtoBuilder zipCode(String zipCode){
            this.zipCode = zipCode;
            return this;
        }

        public AddressResponseDtoBuilder city(String city){
            this.city = city;
            return this;
        }

        public AddressResponseDtoBuilder state(String state){
            this.state = state;
            return this;
        }

        public AddressResponseDtoBuilder requiresCollection(boolean requiresCollection){
            this.requiresCollection = requiresCollection;
            return this;
        }

        public AddressResponseDtoBuilder customerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public AddressResponseDto build(){
            AddressResponseDto address= new AddressResponseDto();
            address.setId(this.id);
            address.setAddressName(this.addressName);
            address.setAddressNumber(this.addressNumber);
            address.setComplement(this.complement);
            address.setZipCode(this.zipCode);
            address.setCity(this.city);
            address.setState(this.state);
            address.setRequiresCollection(this.requiresCollection);
            address.setCustomer(this.customerId);
            return address;
        }
    
    }

    
}
