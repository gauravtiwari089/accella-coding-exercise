package com.accela.exercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.accela.exercise.entity.Address;
import com.accela.exercise.entity.Person;
import com.accela.exercise.exception.AddressNotFoundException;
import com.accela.exercise.exception.PersonNotFoundException;
import com.accela.exercise.repository.AddressRepository;
import com.accela.exercise.repository.PersonRepository;

@SpringBootTest
public class AddressServiceTest {
	
	@Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;
    
    @MockBean
    private PersonRepository personRepository;
    
    @Test
	@DisplayName(value = "Verify add address to person data works fine in service layer")
    public void whenAddAddressIsCalled_ThenAddressShouldBeAddedIntoPerson() throws PersonNotFoundException {
    	
    	final Optional<Person> person = Optional.of(new Person(1L, "Gaurav", "Tiwari"));
    	final Address address = new Address(10L, "street", "city", "state", "postalCode");
    	
    	Mockito.when(personRepository.findById(1L)).thenReturn(person);
    	Mockito.when(addressRepository.save(address)).thenReturn(address);
    	
    	final Address actualAddress = addressService.addAddress(1L, address);
    	
    	assertEquals(address, actualAddress);
    	
    }
    
    @Test
	@DisplayName(value = "Verify add address to person and it throw exception when person doesn't exist")
    public void whenAddAddressIsCalled_ThenExceptionIsThrownForNoPerson() throws PersonNotFoundException {
    	
    	final PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
    		addressService.addAddress(1L, new Address(10L, "street", "city", "state", "postalCode"));
        });
    	
    	assertEquals("Person is not availble", exception.getMessage());
    	
    }
    
    @Test
	@DisplayName(value = "Verify edit address works fine in service layer")
    public void whenEditAddressIsCalled_ThenAddressShouldBeEdited() throws AddressNotFoundException {
    	
    	final Optional<Address> address = Optional.of(new Address(10L, "street", "city", "state", "postalCode"));
    	final Address updateAddress = new Address(10L, "street", "city", "state", "postalCode");
    	
    	Mockito.when(addressRepository.findById(10L)).thenReturn(address);
    	Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(updateAddress);
    	
    	final Address actualAddress = addressService.editAddress(10L, updateAddress);
    	
    	assertEquals(updateAddress, actualAddress);
    	
    }
    
    @Test
	@DisplayName(value = "Verify edit address is called and it throw exception when address doesn't exist")
    public void whenEditAddressIsCalled_ThenExceptionIsThrownForNoAddress() throws AddressNotFoundException {
    	
    	final AddressNotFoundException exception = assertThrows(AddressNotFoundException.class, () -> {
    		addressService.editAddress(10L, new Address(10L, "street", "city", "state", "postalCode"));
        });
    	
    	assertEquals("Address is not availble", exception.getMessage());
    	
    }
    
    @Test
	@DisplayName(value = "Verify delete address works fine in service layer")
    public void whendeleteAddressIsCalled_ThenAddressShouldBeDeleted() throws AddressNotFoundException {
    	final Optional<Address> address = Optional.of(new Address(10L, "street", "city", "state", "postalCode"));
    	
    	Mockito.when(addressRepository.findById(10L)).thenReturn(address);

		Mockito.doNothing().when(addressRepository).delete(address.get());
    	
		addressService.deleteAddress(10L);
    	
    }
    
    @Test
	@DisplayName(value = "Verify delete address is called and it throw exception when address doesn't exist")
    public void whenDeleteAddressIsCalled_ThenExceptionIsThrownForNoAddress() throws AddressNotFoundException {
    	
    	final AddressNotFoundException exception = assertThrows(AddressNotFoundException.class, () -> {
    		addressService.deleteAddress(10L);
        });
    	
    	assertEquals("Address is not availble", exception.getMessage());
    	
    }
    
    @Test
	@DisplayName(value = "Verify get address works fine in service layer")
    public void whenGetAddressIsCalled_ThenAllAddressShouldReturn() {
    	
    	final List<Address> addressList = new ArrayList<>();
    	addressList.add(new Address(10L, "street1", "city1", "state1", "postalCode2"));
    	addressList.add(new Address(11L, "street2", "city2", "state2", "postalCode2"));
    	
    	Mockito.when(addressRepository.findAll()).thenReturn(addressList);
    	
    	final List<Address> actualAddress = addressService.getAddress();
    	
    	assertEquals(addressList, actualAddress);
    	
    }
    
    @Test
	@DisplayName(value = "Verify get address by id works fine in service layer")
    public void whenGetAddressByIdIsCalled_ThenOneAddressShouldReturn() throws AddressNotFoundException {
    	
    	final Optional<Address> address = Optional.of(new Address(10L, "street", "city", "state", "postalCode"));
    	
    	Mockito.when(addressRepository.findById(10L)).thenReturn(address);
    	
    	final Address actualAddress = addressService.getAddressById(10L);
    	
    	assertEquals(address.get(), actualAddress);
    	
    }
    
    @Test
	@DisplayName(value = "Verify get address is called and it throw exception when address doesn't exist")
    public void whenGetAddressIsCalled_ThenExceptionIsThrownForNoAddress() throws AddressNotFoundException {
    	
    	final AddressNotFoundException exception = assertThrows(AddressNotFoundException.class, () -> {
    		addressService.getAddressById(10L);
        });
    	
    	assertEquals("Address is not availble", exception.getMessage());
    	
    }

}
