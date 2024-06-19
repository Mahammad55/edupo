package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.response.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long userId);
    public void addUser(UserDto userDto);
    public void updateUser(Long userId, UserDto userDto);
    public void deleteUser(Long userId);
}
