package az.ramazan.springboot.controller;

import az.ramazan.springboot.entity.User;
import az.ramazan.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    //build create User REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser=userService.createUser(user);
        return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    //build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user=userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }
}
