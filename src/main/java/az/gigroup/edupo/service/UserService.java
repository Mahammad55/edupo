package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.request.UserRequest;
import az.gigroup.edupo.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();

    UserResponse getUserById(Long id);

    UserResponse createUser(UserRequest userRequest);

    void updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
