package com.victorvilar.projetoempresa.embendable;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;



/**
 * Class that represents id of address.
 * @author Victor Vilar
 * @since 2022-01-31
 *
 */
@Embeddable
@Deprecated
public class AddressId implements Serializable{
	
	private String addressName;
	private String addressNumber;

    public AddressId () {
    	
    }



	@Override
	public int hashCode() {
		return Objects.hash(addressName, addressNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressId other = (AddressId) obj;
		return Objects.equals(addressName, other.addressName) && Objects.equals(addressNumber, other.addressNumber);
	}
    
    
	
	
}
