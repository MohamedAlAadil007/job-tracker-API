package com.aadil.jobtracker.UserService;

import com.aadil.jobtracker.UserRepository.UserRepository;
import com.aadil.jobtracker.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void validateUser(UserEntity user) {

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
    }


    @Override
    public UserEntity createUser(UserEntity user) {
        validateUser(user);
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> createUsers(List<UserEntity> users) {
     for(UserEntity user:users){
         validateUser(user);
         // extract names and email
         List<String>names=users.stream().map(UserEntity::getName).toList();
         List<String>emails=users.stream().map(UserEntity::getEmail).toList();

         //single DB calls

         List<UserEntity>findByEmail=userRepository.getByEmail(emails);
         List<UserEntity>findByName=userRepository.findByName(names);

         if (!findByName.isEmpty()){
             throw new RuntimeException("Name is already exists");
         }
         if (!getEmailIn.isEmpty()){
             throw new RuntimeException("Email is already exists");
         }
     }
       return userRepository.saveAll(users);

    }

    @Override
    public Optional<UserEntity> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> updateUser(Long id, UserEntity newUser) {
        return userRepository.findById(id).map(user -> {
            if (newUser.getName()!=null){
                user.setName(newUser.getName());
            }
            if (newUser.getPassword()!=null){
                user.setPassword(newUser.getPassword());
            }
            if (newUser.getEmail()!=null){
                user.setEmail(newUser.getEmail());
            }

            return userRepository.save(user);
        });
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User NOT found: "+id));

        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
       userRepository.deleteAll();
    }

    @Override
    public void deleteById(List<Long> ids) {
        List<UserEntity> users=userRepository.findAllById(ids);

        if (users.size()!=ids.size()){
            throw new RuntimeException("Some Users are NOT found");
        }
         userRepository.deleteAllById(ids);
    }



}