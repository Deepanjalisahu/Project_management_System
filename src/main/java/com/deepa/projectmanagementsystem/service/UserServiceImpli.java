package com.deepa.projectmanagementsystem.service;

import com.deepa.projectmanagementsystem.config.JwtProvider;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpli implements UserService {
   @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserProfileByjwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public User findUserbyId(Long userId) throws Exception {
        Optional<User> optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new Exception("usernot found");
        }
        return optionalUser.get();
    }

    @Override
    public User updateUsersProjectSize(User user, int number) {
        user.setProjectSize(user.getProjectSize()+number);

        return userRepository.save(user);
    }
}
