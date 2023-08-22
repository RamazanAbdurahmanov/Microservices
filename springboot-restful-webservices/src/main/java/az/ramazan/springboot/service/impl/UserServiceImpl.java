package az.ramazan.springboot.service.impl;

import az.ramazan.springboot.entity.User;
import az.ramazan.springboot.repository.UserRepository;
import az.ramazan.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
