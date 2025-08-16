package com.deepa.projectmanagementsystem.service;

import com.deepa.projectmanagementsystem.model.PlanType;
import com.deepa.projectmanagementsystem.model.Subscription;
import com.deepa.projectmanagementsystem.model.User;

public interface SubscriptionService {

	Subscription createSubscription(User user);
	Subscription getUsersSubscription(Long userId)throws Exception;
	Subscription upgradeSubscription(Long userId,PlanType plantype);
	boolean isValid(Subscription subscription);
	
}
