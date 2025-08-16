package com.deepa.projectmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Messages {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String content;

	    private LocalDateTime createdAt;

	    @ManyToOne
	    private Chat chat;

	    @ManyToOne
	    private User sender;
	}