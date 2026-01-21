package com.test.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.project.bean.UserRequest;
import com.test.project.entiry.User;
import com.test.project.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userReq) {
       
    	logger.info("Create User Method Started.......");
    	userService.createUser(userReq);
    	logger.info("Create User Ended Started.......");
    	return ResponseEntity.ok("User Created Successfuly!");
    }
    
    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getAllUsers() {
    	logger.info("getAllUsers Method Started.......");
    	List<User> allUsers = userService.getAllUserSvc();
    	logger.info("getAllUsers Ended Started.......");
    	return ResponseEntity.ok(allUsers);
    }
}
