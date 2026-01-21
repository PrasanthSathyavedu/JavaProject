package com.test.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.project.bean.UserRequest;
import com.test.project.dao.UserDAO;
import com.test.project.entiry.User;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createUser(UserRequest userReq) {
        return userDAO.createUserDao(userReq);
    }
    
    public List<User> getAllUserSvc() {
        return userDAO.getAllUsersDao();
    }
}
