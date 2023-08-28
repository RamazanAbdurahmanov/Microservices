package az.ramazan.springboot.service.impl;

import az.ramazan.springboot.dto.UserDto;
import az.ramazan.springboot.entity.User;
import az.ramazan.springboot.mapper.UserMapper;
import az.ramazan.springboot.repository.UserRepository;
import az.ramazan.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //Convert UserDto into User JPA Entity
        //User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        //Convert User JPA entity to UserDto
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        //return UserMapper.mapToUserDto(user);
        return modelMapper.map(user, UserDto.class);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
//      return users.stream().map(UserMapper::mapToUserDto)
//              .collect(Collectors.toList());
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

