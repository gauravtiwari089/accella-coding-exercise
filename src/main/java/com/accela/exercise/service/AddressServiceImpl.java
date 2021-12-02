package com.accela.exercise.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accela.exercise.entity.Address;
import com.accela.exercise.entity.Person;
import com.accela.exercise.exception.AddressNotFoundException;
import com.accela.exercise.exception.PersonNotFoundException;
import com.accela.exercise.repository.AddressRepository;
import com.accela.exercise.repository.PersonRepository;

/**
 * This Service Class is implemented {@link AddressService} interface and provide implementations.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */
@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	private final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Override
	public Address addAddress(final Long personId, final Address address) throws PersonNotFoundException {
		final Optional<Person> optionalPerson = this.personRepository.findById(personId);
		
		if(!optionalPerson.isPresent()) {
			throw new PersonNotFoundException("Person is not availble");
		}
		
		address.setPerson(optionalPerson.get());
				
		LOGGER.debug("Trying to save address object in db {} ", address);
		return this.addressRepository.save(address);
	}
	
	@Override
	public Address editAddress(final Long id, final Address address) throws AddressNotFoundException {
		final Optional<Address> addressOptional = this.addressRepository.findById(id);
		
		if(!addressOptional.isPresent()) {
			throw new AddressNotFoundException("Address is not availble");
		}
		
		final Address addressFromDB = addressOptional.get();
		
		if(Objects.nonNull(address.getStreet()) && 
				!"".equalsIgnoreCase(address.getStreet())) {
			addressFromDB.setStreet(address.getStreet());
		}
		
		if(Objects.nonNull(address.getCity()) && 
				!"".equalsIgnoreCase(address.getCity())) {
			addressFromDB.setCity(address.getCity());
		}
		
		if(Objects.nonNull(address.getState()) && 
				!"".equalsIgnoreCase(address.getState())) {
			addressFromDB.setState(address.getState());
		}
		
		if(Objects.nonNull(address.getPostalCode()) && 
				!"".equalsIgnoreCase(address.getPostalCode())) {
			addressFromDB.setPostalCode(address.getPostalCode());
		}
		
		LOGGER.debug("Trying to edit address object in db {} ", addressFromDB);
		
		return this.addressRepository.save(addressFromDB);
	}

	@Override
	public void deleteAddress(Long id)  throws AddressNotFoundException {
		
		final Optional<Address> optionalAddress = this.addressRepository.findById(id);
		
		if(!optionalAddress.isPresent()) {
			throw new AddressNotFoundException("Address is not availble");
		}
		
		LOGGER.debug("Trying to delete address object in db for id {} ", id);
		this.addressRepository.delete(optionalAddress.get());
		
	}
	
	@Override
	public List<Address> getAddress() {
		return this.addressRepository.findAll();
	}
	
	@Override
	public Address getAddressById(final Long id)  throws AddressNotFoundException {
		final Optional<Address> optionalAddress = this.addressRepository.findById(id);
		
		if(!optionalAddress.isPresent()) {
			throw new AddressNotFoundException("Address is not availble");
		}
		
		return optionalAddress.get();
	}

	

}
