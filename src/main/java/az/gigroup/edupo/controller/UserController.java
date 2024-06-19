package az.gigroup.edupo.controller;

import az.gigroup.edupo.dto.response.UserDto;
import az.gigroup.edupo.entity.User;
import az.gigroup.edupo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping
    public UserDto getUserById(Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, UserDto userDto){
        userService.updateUser(userId,userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
