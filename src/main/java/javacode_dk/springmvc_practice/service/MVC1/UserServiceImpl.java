package javacode_dk.springmvc_practice.service.MVC1;

import javacode_dk.springmvc_practice.model.MVC1.UserEntity;
import javacode_dk.springmvc_practice.repository.MVC1.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsersFromDatabase() {
        List<UserEntity> fetchAllUsersFromDatabase = userRepository.findAll();

        return fetchAllUsersFromDatabase;
    }

    public UserEntity getUserByEmail(String email) {
        UserEntity userFound = userRepository.findByEmail(email);

        return userFound;
    }

    public UserEntity createUser(UserEntity user) {
        user.setId(0);
        if(!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return null;
        }
        userRepository.save(user);
        return user;
    }

    public UserEntity updateUser(String email, UserEntity user) {

        UserEntity findUserToUpdate = userRepository.findByEmail(email);

        if(findUserToUpdate == null) {
            return null;
        }

        findUserToUpdate.setName(user.getName());
        findUserToUpdate.setAddress(user.getAddress());

        userRepository.save(findUserToUpdate); //На вход нужно передать DTO
        return findUserToUpdate;                //Потом изменить значение переданных полей в найденном объекте и снова сохранить.
    }

    @Transactional
    public boolean deleteUserByEmail(String email) {

        if(userRepository.findByEmail(email) == null){
            return false;
        }
        userRepository.deleteByEmail(email);
        return true;

    }

}
