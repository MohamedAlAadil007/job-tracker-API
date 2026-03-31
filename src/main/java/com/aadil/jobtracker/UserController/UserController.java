package com.aadil.jobtracker.UserController;

import com.aadil.jobtracker.UserService.UserServiceImp;
import com.aadil.jobtracker.UserRepository.UserRepository;
import com.aadil.jobtracker.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImp userService;

    public UserController( UserServiceImp userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createUsers(@RequestBody List<UserEntity> users) {
        return ResponseEntity.status(201).body(userService.createUsers(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(List<UserEntity> users) {
        return ResponseEntity.ok(userService.getAllUsers(users));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllUsersById(@PathVariable List<Long> ids) {
        return ResponseEntity.ok(userService.getAllUsersById(ids));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserEntity newUser) {
        userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());

            return userRepository.save(user);
        });
        return ResponseEntity.status(200).body(userService.updateUser(id, newUser));
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

         userService.deleteUser(id);
        return "User deleted successfully";
    }
    @DeleteMapping("/all")
    public String deleteAll(@RequestParam String confirm){
        if (!"yes".equalsIgnoreCase(confirm)){
            throw new RuntimeException("Enter ?confirm=yes to proceed");
        }
        return userService.deleteAll(confirm);
    }
    @DeleteMapping("by/{id}")
    public ResponseEntity<String> deleteById(@PathVariable List<Long>ids){
        userRepository.findAllById(ids);
        if (ids.isEmpty()){
            throw new RuntimeException("Users are empty");
        }
        return ResponseEntity.ok("Users deleted successfully");
    }
}


