package com.deepa.projectmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepa.projectmanagementsystem.model.Chat;
import com.deepa.projectmanagementsystem.model.Messages;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.request.CreateMessageRequest;
import com.deepa.projectmanagementsystem.service.MessageService;
import com.deepa.projectmanagementsystem.service.ProjectService;
import com.deepa.projectmanagementsystem.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired
	private MessageService messageServices;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectServices;
	
	@PostMapping("/send")
	public ResponseEntity<Messages> sendMessage(@RequestBody CreateMessageRequest request)
	throws Exception{
		User user=userService.findUserbyId(request.getSenderId());
		
		Chat chats=projectServices.getProjectById(request.getProjectId()).getChat();
		
		if(chats==null) throw new Exception("Chats are not foaund");
	   Messages sentmessage=messageServices.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
	   return ResponseEntity.ok(sentmessage);
	
	}
	@GetMapping("/chat/{projectId}")
	public ResponseEntity<List<Messages>> getmessageBychatId(@PathVariable Long projectId)throws Exception{
		List<Messages> messages=messageServices.getMessagesByProjectId(projectId);
		return ResponseEntity.ok(messages);
	}
	
	
}


