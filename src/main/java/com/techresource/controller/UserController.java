package com.techresource.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.techresource.model.User;
import com.techresource.service.UserService;

@RestController
class UserController implements Serializable {

	private static final long serialVersionUID = -7359343224144612882L;
	final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// ----------Retrieve All Users-------------------------------------
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	// ----------Retrieve single user-------------------------------------
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		logger.info("Fetching user with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			logger.info("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	// -----------Create a user--------------------
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User " + user.getUserName());

		logger.info(user.toString());
		if (userService.isUserExist(user)) {
			logger.info("A User with name " + user.getUserName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		logger.info("User -  " + user.getUserName() + " saved successfully.");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -----------Update a user--------------------
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User " + id);

		User currentUser = userService.findById(id);
		if (currentUser == null) {
			logger.info("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		userService.updateUser(user);
		currentUser = userService.findById(id);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	// ------------------- Delete a User--------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			logger.info("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users--------------

	/*
	 * @RequestMapping(value = "/user/", method = RequestMethod.DELETE) public
	 * ResponseEntity<User> deleteAllUsers() {
	 * logger.info("Deleting All Users");
	 * 
	 * userService.deleteAllUsers(); return new
	 * ResponseEntity<User>(HttpStatus.NO_CONTENT); }
	 */
}
