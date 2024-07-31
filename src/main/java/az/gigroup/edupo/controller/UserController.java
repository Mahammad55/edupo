package az.gigroup.edupo.controller;

import az.gigroup.edupo.dto.response.UserResponse;
import az.gigroup.edupo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

//    @GetMapping("/{userId}")
//    public UserResponse getUserById(@PathVariable Long userId) {
//        return userService.getUserById(userId);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addUser(@RequestBody UserResponse userResponse) {
//        userService.addUser(userResponse);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PutMapping("/{userId}")
//    public void updateUser(@PathVariable Long userId, UserResponse userResponse) {
//        userService.updateUser(userId, userResponse);
//    }
//
//    @DeleteMapping("/{userId}")
//    public void deleteUser(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//    }
}
