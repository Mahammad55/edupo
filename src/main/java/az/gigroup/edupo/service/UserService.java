package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.request.UserRequest;
import az.gigroup.edupo.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();

    UserResponse getUserById(Long id);

    UserResponse createUser(UserRequest userRequest);


//    public UserResponse getUserById(Long userId);
//    public void addUser(UserResponse userResponse);
//    public void updateUser(Long userId, UserResponse userResponse);
//    public void deleteUser(Long userId);
}
