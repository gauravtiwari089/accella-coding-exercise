package com.accela.exercise.service;

import java.util.List;

import com.accela.exercise.entity.Address;
import com.accela.exercise.exception.AddressNotFoundException;
import com.accela.exercise.exception.PersonNotFoundException;

/**
 * Service layer between the {@link AddressController} and {@link AddressRepository}.
 * 
 * <p>
 * All the business logic to for the add/edit/delete/get address will be provided by this interface.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */
public interface AddressService {
	
	Address addAddress(final Long personId, final Address address) throws PersonNotFoundException;
	
	Address editAddress(final Long id, final Address address)  throws AddressNotFoundException;
	
	void deleteAddress(final Long id)  throws AddressNotFoundException;
	
	List<Address> getAddress();
	
	Address getAddressById(final Long id)  throws AddressNotFoundException;
	
}
