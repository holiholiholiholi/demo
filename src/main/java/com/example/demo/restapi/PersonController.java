package com.example.demo.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.Timed;
import com.example.demo.data.bean.Person;
import com.example.demo.data.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path="/person")
@Api(value="/person", description = "operations for person")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@Timed
    @ApiOperation(httpMethod = "GET", value = "findAll", nickname = "findAll")
	@RequestMapping(path = "/all", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	@ResponseStatus(HttpStatus.OK)
	public List<Person> findAll() {
		return personService.findAll();
	}
	 
	@Timed
	@RequestMapping(path="/lastname/{lastname}", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public List<Person> findPersonByLastName(@PathVariable("lastname") String lastName) {
		return personService.findByLastName(lastName);
	}
	
	@Timed
	@RequestMapping(path="/new", method = RequestMethod.POST, 
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public void createNewPerson(@RequestBody Person newPerson) {
		personService.createNewPerson(newPerson);
	}
	
	@Timed
	@RequestMapping(path="/id/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@PathVariable Long id) {
		personService.deletePerson(id);
	}
	
	@Timed
	@RequestMapping(path="/update", method = RequestMethod.PATCH,
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public void updatePerson(@RequestBody Person person) {
		personService.updatePerson(person);;
	}
}
