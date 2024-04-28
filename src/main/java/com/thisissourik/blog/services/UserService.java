package com.thisissourik.blog.services;


import java.util.List;
import com.thisissourik.blog.payloads.UserDto;

public interface UserService {
     UserDto createUser(UserDto user);
     UserDto updateUser(UserDto user, Integer userId);
     UserDto getUserById(Integer userId);
     List<UserDto> getAllUser();
     void deleteUser(Integer userId);
}
