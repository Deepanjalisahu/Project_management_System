package com.deepa.projectmanagementsystem.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate subscriptionStartDate;

    private LocalDate subscriptionEndtDate;

    private PlanType planeType;

    public Boolean isValid;

    @OneToOne
    private User user;

	public LocalDate setGetSubscriptionEndDate(LocalDate localDate) {
		return subscriptionEndtDate;
		// TODO Auto-generated method stub
		
	}

	public LocalDate setGetSubscriptionEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
