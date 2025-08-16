package com.deepa.projectmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepa.projectmanagementsystem.model.Messages;

public interface MessageRepository extends JpaRepository<Messages, Long> {

	List<Messages> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
