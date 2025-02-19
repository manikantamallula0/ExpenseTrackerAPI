package com.demo.expensetrackerapi.service;

import com.demo.expensetrackerapi.entity.User;
import com.demo.expensetrackerapi.entity.UserModel;
import com.demo.expensetrackerapi.exception.ItemAlreadyExistsException;
import com.demo.expensetrackerapi.exception.ResourceNotFoundException;
import com.demo.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    public User getUser(Long id){
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id"+ id));

    }

    public UserModel createUser(UserModel userModel){

//       Optional<User> CheckUser = userRepo.findAll().stream().filter(u -> u.getEmail().equals(userModel.getEmail())).findFirst();
        if(userRepo.existsByEmail(userModel.getEmail())){
            throw  new ItemAlreadyExistsException("User already exists with the email" + userModel.getEmail());
        }


        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        userRepo.save(user);
        return userModel;
    }

    public User updateUser(User user, Long id) throws ResourceNotFoundException{
        User exUser = getUser(id);
        exUser.setName(user.getName() != null ? user.getName(): exUser.getName());
        exUser.setAge(user.getAge() != null ? user.getAge(): exUser.getAge());
        exUser.setEmail(user.getEmail() != null ? user.getEmail(): exUser.getEmail());
        exUser.setPassword(user.getPassword() != null ? user.getPassword(): exUser.getPassword());
        return userRepo.save(exUser);
    }

    public User deleteUser(Long id){
        User user = getUser(id);
        userRepo.deleteById(id);
        return user;
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

}
