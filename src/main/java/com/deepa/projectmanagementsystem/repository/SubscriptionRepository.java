package com.deepa.projectmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepa.projectmanagementsystem.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserId(Long userId); 

}
