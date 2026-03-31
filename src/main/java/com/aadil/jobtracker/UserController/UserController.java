package com.aadil.jobtracker.UserController;

import com.aadil.jobtracker.UserService.UserServiceImp;
import com.aadil.jobtracker.Validation.UserRequestDTO;
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
    public ResponseEntity<UserRequestDTO> createUser(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.status(201).body(userService.createUser(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createUsers(@RequestBody List<UserEntity> users) {
        return ResponseEntity.status(201).body(userService.createUsers(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userService.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
            return userService.updateUser(id, user).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.getUser(id).map(user -> {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }).orElse(ResponseEntity.notFound().build());

    }
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(){
         userService.deleteAll();
         return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/bulk")
    public ResponseEntity<?> deleteById(@RequestBody List<Long>ids){
        userService.deleteById(ids);
        return ResponseEntity.noContent().build();
    }
}


