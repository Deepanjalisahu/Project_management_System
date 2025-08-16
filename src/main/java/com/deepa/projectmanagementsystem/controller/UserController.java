package com.deepa.projectmanagementsystem.controller;

import org.springframework.web.bind.annotation.RestController;

import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/users")


public class UserController {

    @Autowired
    private UserService userservice;


    
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception{

     User  user=userservice.findUserProfileByjwt(jwt);
      return new ResponseEntity<>(user,HttpStatus.OK); 
    }



}
