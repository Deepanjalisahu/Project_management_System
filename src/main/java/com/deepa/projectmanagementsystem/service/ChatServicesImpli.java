package com.deepa.projectmanagementsystem.service;

import com.deepa.projectmanagementsystem.model.Chat;
import com.deepa.projectmanagementsystem.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServicesImpli implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    
    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
