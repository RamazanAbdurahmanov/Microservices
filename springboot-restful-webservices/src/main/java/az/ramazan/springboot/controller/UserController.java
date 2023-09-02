package az.ramazan.springboot.controller;

import az.ramazan.springboot.dto.UserDto;
import az.ramazan.springboot.entity.User;
import az.ramazan.springboot.exception.ErrorDetails;
import az.ramazan.springboot.exception.ResourceNotFoundException;
import az.ramazan.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User,Update User,Get User,Get All Users,Delete User"

)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    //build create User REST API
    @Operation(summary = "Create user Rest API",
            description = "Create User REST API is Used to save user in a database")
    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")
    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by id REST API
    @Operation(summary = "Get user Rest API",
            description = "Get User By ID REST API is Used to get a single  user from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 201 SUCCESS")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    //Build Get all users REST API
    @Operation(summary = "Get All users Rest API",
            description = "Get All User By ID REST API is Used to all the users from the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 201 SUCCESS")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Build update user rest api
    @Operation(summary = "Update user Rest API",
            description = "Update User REST API is Used to update a particular user from in the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 201 SUCCESS")
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    //Build Delete useer REST API
    @Operation(summary = "Delete user Rest API",
            description = "Delete User REST API is Used to delete a particular user from  the database")
    @ApiResponse(responseCode = "200",
            description = "Http Status 201 SUCCESS")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);


    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails>handleResourceNotFoundException(
//            ResourceNotFoundException exception,
//            WebRequest webRequest){
//        ErrorDetails errorDetails= new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//
//
//    }
}
