package com.deepa.projectmanagementsystem.service;

import java.util.List;

import com.deepa.projectmanagementsystem.model.Messages;

public interface MessageService {

	Messages sendMessage(Long senderId,Long chatId,String content)throws Exception;
	List<Messages> getMessagesByProjectId(Long projectId)throws Exception;
	
}
