package com.accela.exercise.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.accela.exercise.entity.Address;
import com.accela.exercise.service.AddressService;

@WebMvcTest(AddressController.class)
public class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AddressService addressService;
	
	@Test
	@DisplayName(value = "Verify add address rest interface is working fine")
	public void WhenAddAddressIsCalled_ThenAddAddressRestInterfaceIsCalled_ReturnAddressObject() throws Exception {
		
		final Address expectedAddress = new Address(10L, "street", "city", "state", "postalCode");
		final Address address = new Address();
		address.setStreet("street");
		address.setCity("city");
		address.setState("state");
		address.setPostalCode("postalCode");
		
		Mockito.when(addressService.addAddress(1L, address)).thenReturn(expectedAddress);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1//persons/1/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"street\": \"street\",\r\n"
						+ "  \"city\": \"city\",\r\n"
						+ "  \"state\": \"state\",\r\n"
						+ "  \"postalCode\": \"postalCode\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify edit address rest interface is working fine")
	public void WhenEditAddressIsCalled_ThenEditAddressRestInterfaceIsCalled_ReturnEditedAddressObject() throws Exception {
		
		final Address expectedAddress = new Address(10L, "street", "city", "state", "postalCode");
		final Address address = new Address();
		address.setStreet("street");
		address.setCity("city");
		address.setState("state");
		address.setPostalCode("postalCode");
		Mockito.when(addressService.editAddress(10L, address)).thenReturn(expectedAddress);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/address/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"street\": \"newStreet\",\r\n"
						+ "  \"postalCode\": \"newPostalCode\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify delete address rest interface is working fine")
	public void WhenDeleteAddressIsCalled_ThenDeleteAddressRestInterfaceIsCalled_ReturnSuccessMessage() throws Exception {
		
		Mockito.doNothing().when(addressService).deleteAddress(1L);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/address/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify get address List rest interface is working fine")
	public void WhenGetAddressIsCalled_ThenGetAddressRestInterfaceIsCalled_ReturnListOfAddressObject() throws Exception {
		
		final List<Address> addressList = new ArrayList<>();
    	addressList.add(new Address(10L, "street1", "city1", "state1", "postalCode2"));
    	addressList.add(new Address(11L, "street2", "city2", "state2", "postalCode2"));
    	
		Mockito.when(addressService.getAddress()).thenReturn(addressList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/address")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify get address by id rest interface is working fine")
	public void WhenGetAddressByIdIsCalled_ThenGetAddressByIdRestInterfaceIsCalled_ReturnAddressObject() throws Exception {
		
		final Address expectedAddress = new Address(10L, "street", "city", "state", "postalCode");
		Mockito.when(addressService.getAddressById(1L)).thenReturn(expectedAddress);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/address/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	
	
	
	

}
