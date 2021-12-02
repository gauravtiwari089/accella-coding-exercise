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

import com.accela.exercise.entity.Person;
import com.accela.exercise.exception.PersonNotFoundException;
import com.accela.exercise.service.PersonService;

/**
 * This Controller will provide Rest Interface for the Person resource.
 * 
 * API's included in this Controller are 
 * <ul>
 * <li>Add Person
 * <li>Get Person By Id
 * <li>Edit Person
 * <li>Delete Person
 * <li>Count No Of Person
 * <li>Get Person List
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@RestController
@RequestMapping("/api/v1")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	@PostMapping("/persons")
	public Person addPerson(@Valid @RequestBody final Person person) {
		LOGGER.info("Receive request to add a person");
		return this.personService.addPerson(person);
	}
	
	@GetMapping("/persons/{id}")
    public Person getPersonById(@Valid @PathVariable("id") final Long id) throws PersonNotFoundException {
		LOGGER.info("Receive request to get a person by id");
        return this.personService.getPersonById(id);
    }
	
	@PutMapping("/persons/{id}")
    public Person editPerson(@Valid @PathVariable("id") final Long id,
                                       @RequestBody final Person person) throws PersonNotFoundException {
		LOGGER.info("Receive request to edit a person");
        return this.personService.editPerson(id, person);
    }
	
	@DeleteMapping("/persons/{id}")
	public String deletePerson(@PathVariable("id") final Long id) throws PersonNotFoundException {
		LOGGER.info("Receive request to delete a person");
		this.personService.deletePerson(id);
		return "Person deleted Successfully !!";
	}
	
	@GetMapping("/persons/count")
	public Long countPersonList() {
		LOGGER.info("Receive request to count total no of person");
		return this.personService.countPersonList();
	}
	
	@GetMapping("/persons")
	public List<Person> fetchPersonList() {
		LOGGER.info("Receive request to fetch all persons");
		return this.personService.fetchPersonList();
	}
	
}
