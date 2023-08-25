package az.ramazan.springboot.controller;

import az.ramazan.springboot.dto.UserDto;
import az.ramazan.springboot.entity.User;
import az.ramazan.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private  UserService userService;
    //build create User REST API
    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUser=userService.createUser(user);
        return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    //build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user=userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }
    //Build Get all users REST API
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users =userService.getAllUser();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    //Build update user rest api
    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user){
        user.setId(userId);
        User updatedUser=userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }
    //Build Delete useer REST API
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted",HttpStatus.OK);


    }
}
