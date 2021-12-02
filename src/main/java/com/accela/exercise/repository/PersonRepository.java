package com.accela.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accela.exercise.entity.Person;

/**
 * @author Gaurav Tiwari
 *
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
