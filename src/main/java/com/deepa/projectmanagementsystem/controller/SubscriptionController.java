package com.deepa.projectmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepa.projectmanagementsystem.model.PlanType;
import com.deepa.projectmanagementsystem.model.Subscription;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.service.SubscriptionService;
import com.deepa.projectmanagementsystem.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {


	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private UserService userService;

    
	
	@GetMapping("/path")
	public ResponseEntity<Subscription> getUserSubScription(
			@RequestHeader("Authorization") String jwt
			)throws Exception{
		User user =userService.findUserProfileByjwt(jwt);
		Subscription subscription =subscriptionService.getUsersSubscription(user.getId());
		
		return new ResponseEntity<>(subscription,HttpStatus.OK);
		
	}
	

	@PatchMapping("/upgrade")
	public ResponseEntity<Subscription> UpgradeSubScription(
			@RequestHeader("Authorization") String jwt,
			@RequestParam PlanType plantype) throws Exception{
		User user =userService.findUserProfileByjwt(jwt);
		Subscription subscription =subscriptionService.upgradeSubscription(user.getId(),plantype);
		
		return new ResponseEntity<>(subscription,HttpStatus.OK);
		
	}
	
}
