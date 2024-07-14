package javacode_dk.springmvc_practice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javacode_dk.springmvc_practice.DTO.UserViews;
import javacode_dk.springmvc_practice.model.UserEntity;
import javacode_dk.springmvc_practice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Реализуйте RESTful эндпоинты для:
    //1) Получения списка всех пользователей (без деталей заказов).
    //2) Получения информации о конкретном пользователе (включая детали заказов).
    //3) Создания нового пользователя.
    //4) Обновления информации о пользователе.
    //5) Удаления пользователя.

    @GetMapping
    @JsonView(UserViews.UserDtoWithoutOrders.class)
    public ResponseEntity<List<UserEntity>> fetchAllUsers() {

        List<UserEntity> allUsers = userService.getAllUsersFromDatabase();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{email}")
    @JsonView(UserViews.UserDtoWithOrders.class)
    public ResponseEntity<?> fetchOneUser(@PathVariable String email) {

        UserEntity user = userService.getUserByEmail(email);
        if(user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @JsonView(UserViews.UserDtoWithoutOrders.class)
    public ResponseEntity<UserEntity> newUser(@RequestBody @JsonView(UserViews.UserDtoWithoutOrders.class) UserEntity user) {

        if(userService.createUser(user) == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{email}")
    @JsonView(UserViews.UserDtoWithoutOrders.class)
    public ResponseEntity<UserEntity> newUser(@PathVariable String email, @RequestBody @JsonView(UserViews.UserDtoNameAndAddress.class) UserEntity user) {

        return ResponseEntity.ok(userService.updateUser(email, user));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {

        if (userService.deleteUserByEmail(email)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
