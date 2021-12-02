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

import com.accela.exercise.entity.Person;
import com.accela.exercise.service.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Test
	@DisplayName(value = "Verify add person rest interface is working fine")
	public void WhenAddPersonIsCalled_ThenAddPersonRestInterfaceIsCalled_ReturnPersonObject() throws Exception {
		
		final Person expectedPerson = new Person(1L, "ABC", "XYZ");
		final Person person = new Person();
		person.setFirstName("ABC");
		person.setLastName("XYZ");
		Mockito.when(personService.addPerson(person)).thenReturn(expectedPerson);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"firstName\": \"ABC\",\r\n"
						+ "  \"lastName\": \"XYZ\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify get person by id rest interface is working fine")
	public void WhenGetPersonByIdIsCalled_ThenGetPersonByIdRestInterfaceIsCalled_ReturnPersonObject() throws Exception {
		
		final Person expectedPerson = new Person(1L, "ABC", "XYZ");
		Mockito.when(personService.getPersonById(1L)).thenReturn(expectedPerson);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify edit person rest interface is working fine")
	public void WhenEditPersonIsCalled_ThenEditPersonRestInterfaceIsCalled_ReturnEditedPersonObject() throws Exception {
		
		final Person expectedPerson = new Person(1L, "ABC1", "XYZ1");
		final Person person = new Person();
		person.setFirstName("ABC");
		person.setLastName("XYZ");
		Mockito.when(personService.editPerson(1L, person)).thenReturn(expectedPerson);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/persons/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"firstName\": \"ABC\",\r\n"
						+ "  \"lastName\": \"XYZ\"\r\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify delete person rest interface is working fine")
	public void WhenDeletePersonIsCalled_ThenDeletePersonRestInterfaceIsCalled_ReturnSuccessMessage() throws Exception {
		
		Mockito.doNothing().when(personService).deletePerson(1L);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/persons/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify count person rest interface is working fine")
	public void WhenCountPersonIsCalled_ThenCountPersonRestInterfaceIsCalled_ReturnTotalPersonCount() throws Exception {
		
		Mockito.when(personService.countPersonList()).thenReturn(2L);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons/count")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	@DisplayName(value = "Verify fetch Person List rest interface is working fine")
	public void WhenFetchPersonListIsCalled_ThenfetchPersonListRestInterfaceIsCalled_ReturnListOfPersonObject() throws Exception {
		
		final List<Person> personList = new ArrayList<>();
    	personList.add(new Person(1L, "IJK", "PQR"));
    	personList.add(new Person(2L, "ABC", "XYZ"));
    	
		Mockito.when(personService.fetchPersonList()).thenReturn(personList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}

}
