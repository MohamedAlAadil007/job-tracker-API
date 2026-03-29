package com.aadil.jobtracker.UserController;

import com.aadil.jobtracker.UserRepository.UserRepository;
import com.aadil.jobtracker.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
   private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping ("/bulk")
    public List<UserEntity> createMultipleUsers (@RequestBody List<UserEntity> users){
        return userRepository.saveAll(users);
    }
    @PostMapping
    public UserEntity createUser(UserEntity user){
        return userRepository.save(user);
    }
    @GetMapping
    public List<UserEntity> getAllUser(List<UserEntity>users){
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<UserEntity> getOneUser(@PathVariable Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("User NOT found: "+id);
        }
        return userRepository.findById(id);
    }
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity newUsers){
        UserEntity existingUsers=userRepository.findById(id).orElseThrow(()->new RuntimeException("User NOT found: "+id));

        existingUsers.setName(newUsers.getName());
        existingUsers.setEmail(newUsers.getEmail());
        existingUsers.setPassword(newUsers.getPassword());

        return existingUsers;
    }
    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable Long id){
        userRepository.deleteById(id);

        return "User deleted Successfully";
    }
    @DeleteMapping("/all")
    public String deleteAllUsers(@RequestParam String confirm){
        if (!"yes".equalsIgnoreCase(confirm)){
            throw new RuntimeException("Enter ?confirm=yes to proceed");
        }
        userRepository.deleteAll();
        return "Every Users have deleted Successfully";
    }

    @DeleteMapping("/byid")
    public String deleteMultipleUsersById(List<Long>ids){
        List<UserEntity> users=userRepository.findAllById(ids);
        
        if (users.isEmpty()){
            throw new RuntimeException("Table is already empty");
        }
        userRepository.deleteAllById(ids);
        
        return users.size()+" Users have deleted";
    }

}
