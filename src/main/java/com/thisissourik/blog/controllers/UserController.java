package com.thisissourik.blog.controllers;

import com.thisissourik.blog.entitites.User;
import com.thisissourik.blog.payloads.ApiResponse;
import com.thisissourik.blog.payloads.UserDto;
import com.thisissourik.blog.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST Method - Create
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user)
    {
        UserDto createdUser = userService.createUser(user);
        System.out.println("Created New User");
        return new ResponseEntity<>(createdUser, HttpStatus.ACCEPTED);
    }

    //PUT Method - Update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user,@PathVariable("userId") Integer userId)
    {
        UserDto updatedUser = this.userService.updateUser(user, userId);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE Method - Delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse(" User deleted successfully ! ",true),HttpStatus.OK);
    }

    //GET Method - Get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUsers()
    {
        return ResponseEntity.ok(this.userService.getAllUser());
    }


    //GET Method - Get user by Id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
