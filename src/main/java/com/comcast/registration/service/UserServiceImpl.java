package com.comcast.registration.service;

import com.comcast.registration.dao.UserRepository;
import com.comcast.registration.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

/**
 * Created by vrgude on 7/13/2016.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService
{

	@Inject
	UserRepository userRepository;

	public User findById(String id)
	{
		return userRepository.findById(id);
	}

	public void saveUser(User user)
	{
		userRepository.saveUser(user);
	}

	public void updateUser(User user)
	{
		userRepository.updateUser(user);
	}

	public void deleteUser(User user)
	{
		userRepository.deleteUser(user);
	}

	public List<User> findAllUsers()
	{
		return userRepository.findAllUsers();
	}

	public boolean isUserExist(User user)
	{
		return userRepository.isUserExist(user);
	}
}
