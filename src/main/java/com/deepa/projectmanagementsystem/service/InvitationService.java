package com.deepa.projectmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.Invitation;

import jakarta.mail.MessagingException;

@Service
public interface InvitationService {

	public void sendInvitation(String email,Long projectId) throws MessagingException;
	public Invitation acceptInvitation(String token,Long userId) throws Exception;
	
	public String getTokenByUserMail(String userEmail);
	
	void deleteToken(String token);
}
