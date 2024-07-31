package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.request.UserRequest;
import az.gigroup.edupo.dto.response.UserResponse;
import az.gigroup.edupo.entity.Authority;
import az.gigroup.edupo.entity.User;
import az.gigroup.edupo.exception.NotFoundException;
import az.gigroup.edupo.mapper.UserMapper;
import az.gigroup.edupo.repository.AuthorityRepository;
import az.gigroup.edupo.repository.UserRepository;
import az.gigroup.edupo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::entityToResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException("User by id=%d not found".formatted(id)));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.requestToEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority authority = authorityRepository.findAuthoritiesByAuthority(userRequest.getRole().name()).orElseGet(() -> authorityRepository.save(new Authority().authority(userRequest.getRole().name())));
        user.setAuthorities(Set.of(authority));
        return userMapper.entityToResponse(userRepository.save(user));
    }

//
//    @Override
//    public void updateUser(Long userId, UserResponse userResponse) {
//        User user = userRepository.findById(userId).orElseThrow();
//        user.setEmail(userResponse.getEmail());
////        user.setRole(userDto.getRole());
//        userRepository.save(user);
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }
}
