package az.ramazan.springboot.service.impl;

import az.ramazan.springboot.dto.UserDto;
import az.ramazan.springboot.entity.User;
import az.ramazan.springboot.exception.EmailAlreadyExistsException;
import az.ramazan.springboot.exception.ResourceNotFoundException;
import az.ramazan.springboot.mapper.AutoUserMapper;
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
        Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already exists for User");
        }
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        //Convert User JPA entity to UserDto
        //UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",userId)
        );
        //return UserMapper.mapToUserDto(user);
        //return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
//      return users.stream().map(UserMapper::mapToUserDto)
//              .collect(Collectors.toList());
//        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                ()->new ResourceNotFoundException("User","id",user.getId())
        );

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(userId);
    }
}

