package com.demo.expensetrackerapi.service;


import com.demo.expensetrackerapi.entity.User;
import com.demo.expensetrackerapi.entity.UserModel;
import com.demo.expensetrackerapi.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    User getUser(Long id);

    UserModel createUser(UserModel userModel);

    User updateUser(User user, Long id) throws ResourceNotFoundException;

    List<User> getUsers();

}
