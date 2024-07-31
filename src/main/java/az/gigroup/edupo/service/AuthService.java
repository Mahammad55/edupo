package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.request.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
