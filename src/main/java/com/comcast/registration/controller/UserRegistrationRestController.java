package com.comcast.registration.controller;

import com.comcast.registration.domain.User;
import com.comcast.registration.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Vardhana Rao Gude on 7/13/2016.
 */
@Controller
@RequestMapping("/")
public class UserRegistrationRestController
{

	protected static final Logger LOG = LoggerFactory.getLogger(UserRegistrationRestController.class);

	@Inject
	UserService userService;

	@Inject
	MessageSource messageSource;


	/**
	 *  This method is used to retrieve all the users from the database
	 * @return
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> listAllUsers()
	{
		List<User> users = userService.findAllUsers();
		if (users.isEmpty())
		{
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * This method is used to retrieve single user
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") String id)
	{
		LOG.info("Fetching User with id={}", new Object[]{id});
		User user = userService.findById(id);
		if (user == null)
		{
			LOG.info("Fetching User with id={}", new Object[]{id} + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


	/**
	 * This method is used to insert a new user into database
	 * @param user
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
	{
		LOG.info("Creating User={}", new Object[]{user});
		if (userService.isUserExist(user))
		{
			LOG.info("A User with name={}", new Object[]{user.getUserName()} + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * This method is used to update existing user
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user)
	{
		LOG.info("Updating User with id={}", new Object[]{id});
		User currentUser = userService.findById(id);

		if (currentUser == null)
		{
			LOG.info("User with id={}", new Object[]{id} + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		User persistedUser = userService.findById(id);
		persistedUser.setUserName(user.getUserName());
		persistedUser.setEmail(user.getEmail());
		persistedUser.setRegisteredDate(user.getRegisteredDate());

		userService.updateUser(persistedUser);
		return new ResponseEntity<User>(persistedUser, HttpStatus.OK);
	}

	/**
	 * This method is used to delete a user
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id)
	{
		LOG.info("Fetching & Deleting User with id={}", new Object[]{id});
		User user = userService.findById(id);
		if (user == null)
		{
			LOG.info("Unable to delete. User with id ={}", new Object[]{id} + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUser(user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
