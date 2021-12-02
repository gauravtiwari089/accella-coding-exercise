package com.accela.exercise.service;

import java.util.List;

import com.accela.exercise.entity.Person;
import com.accela.exercise.exception.PersonNotFoundException;

/**
 * Service layer between the {@link PersonController} and {@link PersonRepository}.
 * 
 * <p>
 * All the business logic to for the add/edit/delete/get/count person will be provided by this interface.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */
public interface PersonService {
	
	Person addPerson(final Person person);
	
	Person getPersonById(final Long id) throws PersonNotFoundException;
	
	Person editPerson(final Long id, final Person person) throws PersonNotFoundException;
		
	void deletePerson(final Long id) throws PersonNotFoundException;
	
	Long countPersonList();
	
	List<Person> fetchPersonList();

}
