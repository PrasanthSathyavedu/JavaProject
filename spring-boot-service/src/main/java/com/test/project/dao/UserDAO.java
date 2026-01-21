package com.test.project.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.test.project.bean.UserRequest;
import com.test.project.entiry.User;
import com.test.project.exception.DuplicateEmailException;
import com.test.project.repository.UserRepository;


@Repository
public class UserDAO {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserDAO(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User createUserDao(UserRequest userReq) {
		User user = new User();
		String encryptPassword = passwordEncoder.encode(userReq.getPassword());

		user.setName(userReq.getName());
		user.setEmail(userReq.getEmail());
		user.setPassword(encryptPassword);
		try {
	        return userRepository.save(user);
	    } catch (DataIntegrityViolationException ex) {
	        if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("users_email_key")) {
	            throw new DuplicateEmailException("A user with this email already exists.");
	        }
	        throw ex;
	    }
	}
	
	public List<User> getAllUsersDao() {
		try {
			List<User> users = userRepository.findAll();
	        return users;
	    } catch (DataIntegrityViolationException ex) {
	        throw new DuplicateEmailException("User Not Found.");
	    }
	}
}
