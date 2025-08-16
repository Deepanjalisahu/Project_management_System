package com.deepa.projectmanagementsystem.service;

import com.deepa.projectmanagementsystem.model.User;

public interface UserService {
   User findUserProfileByjwt(String jwt)throws Exception;

   User findUserByEmail(String email)throws Exception;

   User findUserbyId(Long userId)throws  Exception;

   User updateUsersProjectSize(User user,int number);

}
