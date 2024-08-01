package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.request.LoginRequest;
import az.gigroup.edupo.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
