package com.accela.exercise.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity class for Address. Each field in this class will be a column in Address Table in database.
 * Also this class in one-to-one mapping with {@link Person} class. 
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank (message = "Please provide street name")
	private String street;
	@NotBlank (message = "Please provide first name of the person")
	private String city;
	@NotBlank (message = "Please provide first name of the person")
	private String state;
	@NotBlank (message = "Please provide first name of the person")
	private String postalCode;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "person_id", nullable = false)
	@JsonIgnore
	private Person person;
	
	
	public Address() {
	}

	public Address(final Long id, final String street, final String city, 
			final String state, final String postalCode) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
	
	public Person getPerson() { 
		return person; 
	}
	  
	public void setPerson(Person person) { 
		this.person = person; 
	}
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state="
				+ state + ", postalCode=" + postalCode + "]";
	}


}
