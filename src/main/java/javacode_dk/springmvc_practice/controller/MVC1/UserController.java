package javacode_dk.springmvc_practice.controller.MVC1;

import com.fasterxml.jackson.annotation.JsonView;
import javacode_dk.springmvc_practice.DTO.MVC1.UserViews;
import javacode_dk.springmvc_practice.model.MVC1.UserEntity;
import javacode_dk.springmvc_practice.service.MVC1.UserService;
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

    @GetMapping("/{bookName}")
    @JsonView(UserViews.UserDtoWithOrders.class)
    public ResponseEntity<?> fetchOneUser(@PathVariable String bookName) {

        UserEntity user = userService.getUserByEmail(bookName);
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

    @PatchMapping("/{bookName}")
    @JsonView(UserViews.UserDtoWithoutOrders.class)
    public ResponseEntity<UserEntity> newUser(@PathVariable String bookName, @RequestBody @JsonView(UserViews.UserDtoNameAndAddress.class) UserEntity user) {

        return ResponseEntity.ok(userService.updateUser(bookName, user));
    }

    @DeleteMapping("/{bookName}")
    public ResponseEntity<?> deleteUser(@PathVariable String bookName) {

        if (userService.deleteUserByEmail(bookName)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
