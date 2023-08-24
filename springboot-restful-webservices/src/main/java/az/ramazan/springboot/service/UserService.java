package az.ramazan.springboot.service;

import az.ramazan.springboot.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    List<User> getAllUser();
}
