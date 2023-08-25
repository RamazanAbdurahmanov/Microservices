package az.ramazan.springboot.service;

import az.ramazan.springboot.dto.UserDto;
import az.ramazan.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUser();
    User updateUser(User user);
    void deleteUser(Long userId);
}
