package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.response.UserDto;
import az.gigroup.edupo.entity.User;
import az.gigroup.edupo.mapper.UserMapper;
import az.gigroup.edupo.repository.UserRepository;
import az.gigroup.edupo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().
                stream().map(userMapper::mapToDto).
                collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return userMapper.mapToDto(user);
    }

    @Override
    public void addUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.mapToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
