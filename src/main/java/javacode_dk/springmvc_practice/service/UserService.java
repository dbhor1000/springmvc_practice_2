package javacode_dk.springmvc_practice.service;

import javacode_dk.springmvc_practice.model.UserEntity;
import java.util.List;

public interface UserService {

    public List<UserEntity> getAllUsersFromDatabase();
    public UserEntity getUserByEmail(String email);
    public UserEntity createUser(UserEntity user);
    public UserEntity updateUser(String email, UserEntity user);
    public boolean deleteUserByEmail(String email);
}
