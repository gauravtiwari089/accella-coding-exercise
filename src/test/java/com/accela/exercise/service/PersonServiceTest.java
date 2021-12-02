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

import com.accela.exercise.entity.Person;
import com.accela.exercise.exception.PersonNotFoundException;
import com.accela.exercise.repository.PersonRepository;


@SpringBootTest
public class PersonServiceTest {
	
	@Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;
    
	@Test
	@DisplayName(value = "Verify add valid person data works fine in service layer")
    public void whenValidPersonIsCalled_ThenPersonShouldBeAdded() {
    	
    	final Person person = new Person(1L, "ABC", "XYZ");
    	
    	Mockito.when(personRepository.save(person)).thenReturn(person);
    	
    	final Person actualPerson = personService.addPerson(person);
    	
    	assertEquals(person, actualPerson);
    	
    	Mockito.verify(personRepository, Mockito.times(1)).save(person);
    	
    }
	
	@Test
	@DisplayName(value = "Verify get person by id works fine in service layer")
    public void whenGetPersonByIdIsCalled_ThenOnePersonShouldReturn() throws PersonNotFoundException {
    	
		final Optional<Person> person = Optional.of(new Person(1L, "ABC", "XYZ"));
    	
    	Mockito.when(personRepository.findById(1L)).thenReturn(person);
    	
    	final Person actualPerson = personService.getPersonById(1L);
    	
    	assertEquals(person.get(), actualPerson);
    	
    }
	
	@Test
	@DisplayName(value = "Verify get person by id and it throw exception when person doesn't exist")
    public void whenGetPersonByIdIsCalled_ThenExceptionIsThrownForNoPerson() throws PersonNotFoundException {
    	
		final PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
    		personService.getPersonById(1L);
        });
    	
    	assertEquals("Person is not availble", exception.getMessage());
    	
    }
	
	@Test
	@DisplayName(value = "Verify edit person list works fine in service layer")
    public void whenEditPersonIsCalled_ThenPersonShouldUpdate() throws PersonNotFoundException {
    
    	final Optional<Person> person = Optional.of(new Person(1L, "ABC", "XYZ"));
    	final Person updatePerson = new Person(1L, "DEF", "PQR");
    	
    	Mockito.when(this.personRepository.findById(1L)).thenReturn(person);
    	Mockito.when(this.personRepository.save(Mockito.any(Person.class))).thenReturn(updatePerson);    	
    	
    	final Person actualPerson = personService.editPerson(1L, updatePerson);
    	
    	assertEquals(updatePerson, actualPerson);
    }
	
	@Test
	@DisplayName(value = "Verify edit person and it throw exception when person doesn't exist")
    public void whenEditPersonIsCalled_ThenExceptionIsThrownForNoPerson() throws PersonNotFoundException {
    
		final PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
    		personService.editPerson(1L, new Person(1L, "ABC", "XYZ"));
        });
    	
    	assertEquals("Person is not availble", exception.getMessage());
    }
	
	@Test
	@DisplayName(value = "Verify delete person works fine in service layer")
    public void whendeletePersonIsCalled_ThenPersonShouldBeDeleted()  throws PersonNotFoundException {

		final Optional<Person> person = Optional.of(new Person(1L, "ABC", "XYZ"));
		
		Mockito.when(this.personRepository.findById(1L)).thenReturn(person);
		
		Mockito.doNothing().when(personRepository).deleteById(1L);
    	
    	personService.deletePerson(1L);
    	
    }
	
	@Test
	@DisplayName(value = "Verify delete person and it throw exception when person doesn't exist")
    public void whenDeletePersonIsCalled_ThenExceptionIsThrownForNoPerson() throws PersonNotFoundException {
    
		final PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
    		personService.deletePerson(1L);
        });
    	
    	assertEquals("Person is not availble", exception.getMessage());
    }
	
	@Test
	@DisplayName(value = "Verify fetch person list works fine in service layer")
    public void whenFetchPersonListIsCalled_ThenPersonShouldSave() {
    	
    	final List<Person> personList = new ArrayList<>();
    	personList.add(new Person(1L, "ABC", "XYZ"));
    	personList.add(new Person(2L, "DEF", "PQR"));
    	
    	Mockito.when(personRepository.findAll()).thenReturn(personList);
    	
    	final List<Person> actualPerson = personService.fetchPersonList();
    	
    	assertEquals(personList, actualPerson);
    	
    }
	
	
	@Test
	@DisplayName(value = "Verify count of person list works fine in service layer")
    public void whenCountPersonListIsCalled_ThenCountOfPersonIsReturned() {
    	
    	Mockito.when(personRepository.count()).thenReturn(2L);
    	
    	final Long count = personService.countPersonList();
    	
    	assertEquals(2, count);
    	
    }

}
