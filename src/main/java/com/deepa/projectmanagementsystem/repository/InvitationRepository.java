package com.deepa.projectmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepa.projectmanagementsystem.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

	Invitation findByToken(String Token);
	//Invitation findByMail(String userEmail);
	Invitation findByEmail(String email);

}
