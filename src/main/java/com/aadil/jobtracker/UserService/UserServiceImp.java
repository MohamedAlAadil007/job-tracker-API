package com.aadil.jobtracker.UserService;

import com.aadil.jobtracker.UserRepository.UserRepository;
import com.aadil.jobtracker.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        if (user.getName()==null||user.getName().isEmpty()){
            throw new RuntimeException("User Name required");
        }
        if (user.getEmail()==null||user.getEmail().isEmpty()){
            throw new RuntimeException("User Email required");
        }
        if (user.getPassword()==null||user.getPassword().isEmpty()){
            throw new RuntimeException("User Password required");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User Email already exists");
        }
        if (userRepository.existsByName(user.getName())){
            throw new RuntimeException("User Name already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> createUsers(List<UserEntity> users) {
        for (UserEntity user:users){
            if (user.getPassword()==null||user.getPassword().isEmpty()){
                throw new RuntimeException("Password field is empty");
            }
            if (user.getName()==null||user.getName().isEmpty()){
                throw new RuntimeException("Name field is empty");
            }
            if (user.getEmail()==null||user.getEmail().isEmpty()){
                throw new RuntimeException("Email field is empty");
            }
            if (userRepository.existsByName(user.getName())){
                throw new RuntimeException("User Name is already exists");
            }
            if (userRepository.existsByEmail(user.getEmail())){
                throw new RuntimeException("User Email is already exists");
            }
        }
        return userRepository.saveAll(users);
    }

    @Override
    public UserEntity getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User NOT found: "+id));
    }

    @Override
    public List<UserEntity> getAllUsers(List<UserEntity> users) {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getAllUsersById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity newUser) {
        UserEntity existingUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("User NOT found: "+id));

        existingUser.setName(newUser.getName());
        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(newUser.getPassword());

        return userRepository.save(existingUser);

    }

    @Override
    public String deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(()->new RuntimeException("User NOT found: "+id));

         userRepository.deleteById(id);
         return "User deleted Successfully";
    }

    @Override
    public String deleteAll(String confirm) {
        if (!"yes".equalsIgnoreCase(confirm)){
            throw new RuntimeException("Enter ?confirm=yes to Proceed");
        }
         userRepository.deleteAll();
        return "All Users are deleted";
    }

    @Override
    public String deleteById(List<Long> ids) {
        List<UserEntity> users=userRepository.findAllById(ids);

        if (users.isEmpty()){
            throw new RuntimeException("Users are already deleted");
        }
         userRepository.deleteAllById(ids);
        return users.size()+ " Users are deleted";
    }
}