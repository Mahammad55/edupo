package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.request.LoginRequest;
import az.gigroup.edupo.entity.User;
import az.gigroup.edupo.exception.NotFoundException;
import az.gigroup.edupo.exception.UnauthorizedException;
import az.gigroup.edupo.repository.UserRepository;
import az.gigroup.edupo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User by email=%s not found".formatted(email)));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) return user.getName();
        throw new UnauthorizedException("User password or username is not correct");
    }
}
