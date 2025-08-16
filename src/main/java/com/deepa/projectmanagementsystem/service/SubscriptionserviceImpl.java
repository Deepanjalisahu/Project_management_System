package com.deepa.projectmanagementsystem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.PlanType;
import com.deepa.projectmanagementsystem.model.Subscription;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.repository.SubscriptionRepository;

@Service
public class SubscriptionserviceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionrepository;
	@Autowired
	private UserService userService;
	
	
	
	@Override
	public Subscription createSubscription(User user) {
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setSubscriptionStartDate(LocalDate.now());
		subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
		subscription.setIsValid(true);
		subscription.setPlaneType(PlanType.FREE);
		
		return subscriptionrepository.save(subscription);
	}

	@Override
	public Subscription getUsersSubscription(Long userId) throws Exception {
		
		Subscription subscription= subscriptionrepository.findByUserId(userId);
	 if(!isValid(subscription)) {
		 subscription.setPlaneType(PlanType.FREE);
		 subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
		 subscription.setSubscriptionStartDate(LocalDate.now());
	 }
	 return subscriptionrepository.save(subscription);
	}

	@Override
	public Subscription upgradeSubscription(Long userId, PlanType plantype) {
	Subscription subscription=subscriptionrepository.findByUserId(userId);
	subscription.setPlaneType(plantype);
	subscription.setSubscriptionStartDate(LocalDate.now());
	if(plantype.equals(plantype.ANNUALLY)) {
		subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
	}
	else {
		subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));
	}
		return subscriptionrepository.save(subscription);
	}

	@Override
	public boolean isValid(Subscription subscription) {
		if(subscription.getPlaneType().equals(PlanType.FREE)) {
			return true;
		}
		LocalDate endDate=subscription.setGetSubscriptionEndDate();
		LocalDate currentDate=LocalDate.now();
		return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
	}
	

}
