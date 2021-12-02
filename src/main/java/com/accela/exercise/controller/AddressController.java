package com.accela.exercise.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accela.exercise.entity.Address;
import com.accela.exercise.exception.AddressNotFoundException;
import com.accela.exercise.exception.PersonNotFoundException;
import com.accela.exercise.service.AddressService;

/**
 * This Controller will provide Rest Interface for the Address resource.
 * 
 * API's included in this Controller are 
 * <ul>
 * <li>Add Address
 * <li>Edit Address
 * <li>Delete Address
 * <li>Get Address
 * <li>Get Address By Id
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@RestController
@RequestMapping("/api/v1")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	
	@PostMapping("/persons/{personId}/address")
	public Address addAddress(@PathVariable("personId") final Long personId,
			@Valid @RequestBody final Address address) throws PersonNotFoundException{
		LOGGER.info("Receive request to add address to the person");
		return this.addressService.addAddress(personId, address);
	}
	
	@PutMapping("/address/{id}")
    public Address editAddress(@Valid @PathVariable("id") final Long id,
                                       @RequestBody final Address address) throws  AddressNotFoundException {
		LOGGER.info("Receive request to edit address");
        return this.addressService.editAddress(id, address);
    }
	
	@DeleteMapping("/address/{id}")
	public String deleteAddress(@PathVariable("id") final Long id)  throws AddressNotFoundException {
		LOGGER.info("Receive request to delete address");
		this.addressService.deleteAddress(id);
		return "Address deleted Successfully !!";
	}
	
	@GetMapping("/address")
	public List<Address> getAddress()  throws AddressNotFoundException {
		LOGGER.info("Receive request to get address");
		return this.addressService.getAddress();
	}
	
	@GetMapping("/address/{id}")
	public Address getAddressById(@PathVariable("id") final Long id)  throws AddressNotFoundException {
		LOGGER.info("Receive request to get a address by id");
		return this.addressService.getAddressById(id);
		
	}

}
