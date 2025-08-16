package com.deepa.projectmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.model.Chat;
import com.deepa.projectmanagementsystem.model.Messages;
import com.deepa.projectmanagementsystem.repository.MessageRepository;
import com.deepa.projectmanagementsystem.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messagerepository;
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	public Messages sendMessage(Long senderId, Long chatId, String content) throws Exception {
		
		User sender=userrepository.findById(senderId)
				.orElseThrow(()-> new Exception("User not found with id:"+senderId));
		Chat chat = projectService.getProjectById(chatId).getChat();
		

		
		Messages message =new Messages();
		message.setContent(content);
		message.setSender(sender);
		message.setCreatedAt(LocalDateTime.now());
		message.setChat(chat);
		Messages savedMessage=messagerepository.save(message);
		
		chat.getMessages().add(savedMessage); 

		return savedMessage;
	}

	@Override
	public List<Messages> getMessagesByProjectId(Long projectId) throws Exception {
		Chat chat=projectService.getChatByProjectId(projectId);
		List<Messages> findByChatIdOrderByCreatedAtAsc=messagerepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
	
		return findByChatIdOrderByCreatedAtAsc;
	}
	

}
