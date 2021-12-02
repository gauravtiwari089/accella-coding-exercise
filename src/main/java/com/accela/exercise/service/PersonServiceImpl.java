package com.accela.exercise.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accela.exercise.entity.Person;
import com.accela.exercise.exception.PersonNotFoundException;
import com.accela.exercise.repository.PersonRepository;

/**
 * This Service Class is implemented {@link PersonService} interface and provide implementations.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	private final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Override
	public Person addPerson(final Person person) {
		LOGGER.debug("Adding person object in the database {} ", person);
		return this.personRepository.save(person);
	}
	
	@Override
	public Person getPersonById(final Long id) throws PersonNotFoundException {
		final Optional<Person> optionalPerson = this.personRepository.findById(id);
		
		if(!optionalPerson.isPresent()) {
			throw new PersonNotFoundException("Person is not availble");
		}
		
		return optionalPerson.get();
	}
	
	@Override
	public Person editPerson(final Long id, final Person person) throws PersonNotFoundException {
		final Optional<Person> optionalPerson = this.personRepository.findById(id);
		
		if(!optionalPerson.isPresent()) {
			throw new PersonNotFoundException("Person is not availble");
		}
		
		final Person personFromDB = optionalPerson.get();
		
		if(Objects.nonNull(person.getFirstName()) && 
				!"".equalsIgnoreCase(person.getFirstName())) {
			personFromDB.setFirstName(person.getFirstName());
		}
		
		if(Objects.nonNull(person.getLastName()) && 
				!"".equalsIgnoreCase(person.getLastName())) {
			personFromDB.setLastName(person.getLastName());
		}
		
		LOGGER.debug("editing person object in the database {} ", personFromDB);
		return this.personRepository.save(personFromDB);
	}
	
	@Override
	public void deletePerson(final Long id) throws PersonNotFoundException {
		final Optional<Person> optionalPerson = this.personRepository.findById(id);
		
		if(!optionalPerson.isPresent()) {
			throw new PersonNotFoundException("Person is not availble");
		}
		
		this.personRepository.delete(optionalPerson.get());
		LOGGER.debug("Person with Id {} deleted from the database", id);
	}
	
	@Override
	public List<Person> fetchPersonList() {
		return this.personRepository.findAll();
	}
	
	@Override
	public Long countPersonList() {
		return this.personRepository.count();
	}
	
	
	
	
}
