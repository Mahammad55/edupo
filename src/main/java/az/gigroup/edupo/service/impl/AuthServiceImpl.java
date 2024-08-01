package az.gigroup.edupo.service.impl;

import az.gigroup.edupo.dto.request.LoginRequest;
import az.gigroup.edupo.dto.response.LoginResponse;
import az.gigroup.edupo.entity.User;
import az.gigroup.edupo.exception.NotFoundException;
import az.gigroup.edupo.mapper.UserMapper;
import az.gigroup.edupo.repository.UserRepository;
import az.gigroup.edupo.security.JwtService;
import az.gigroup.edupo.security.MyUserDetails;
import az.gigroup.edupo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserMapper mapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User by email=%s not found".formatted(email)));

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        MyUserDetails principle = (MyUserDetails) authenticate.getPrincipal();
        return new LoginResponse(jwtService.generateAccessToken(principle), mapper.entityToResponse(user));
    }
}
