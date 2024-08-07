package com.victorvilar.projetoempresa.dto.adress;

public interface AddressDto {

    public String getAddressName();
    public void setAddressName(String addressName);
    public String getAddressNumber();
    public void setAddressNumber(String addressNumber);
    public String getComplement();
    public void setComplement(String complement);
    public String getZipCode();
    public void setZipCode(String zipCode);
    public String getCity();
    public void setCity(String city);
    public String getState();
    public void setState(String state);
    public boolean isRequiresCollection();
    public void setRequiresCollection(boolean requiresCollection);
    public String getCustomer();
    public void setCustomer(String customer);

}
