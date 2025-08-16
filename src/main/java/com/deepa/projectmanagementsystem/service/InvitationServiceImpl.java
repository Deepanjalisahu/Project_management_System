package com.deepa.projectmanagementsystem.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.Invitation;
import com.deepa.projectmanagementsystem.repository.InvitationRepository;

import jakarta.mail.MessagingException;

@Service
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	 private InvitationRepository invitationrepository;
	
	@Autowired
	private EmailService emailservice;
	
	
	@Override
	public void sendInvitation(String email, Long projectId) throws MessagingException {
		
		String InvitationToken=UUID.randomUUID().toString();
		Invitation invitation=new Invitation();
		invitation.setEmail(email);
		invitation.setProjectid(projectId);
		invitation.setToken(InvitationToken);
		
		invitationrepository.save(invitation);
		String invitationLink="http://localhost:6789/accept_invitation?token"+InvitationToken;
		emailservice.sendEmailWithToken(email, invitationLink);
	}

	@Override
	public Invitation acceptInvitation(String token, Long userId) throws Exception {
		Invitation invitation=invitationrepository.findByToken(token);
		if(invitation==null) {
			throw new Exception("invalid invitation token");
			
		}
		return invitation;
	}

	@Override
	public String getTokenByUserMail(String userEmail) {
		Invitation invitation=invitationrepository.findByEmail(userEmail);
		
		return invitation.getToken();
	}

	@Override
	public void deleteToken(String token) {
		Invitation invitation = invitationrepository.findByToken(token);
		invitationrepository.delete(invitation);
		
	}

}
