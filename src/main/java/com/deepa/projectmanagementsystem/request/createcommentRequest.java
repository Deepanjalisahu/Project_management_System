package com.deepa.projectmanagementsystem.request;

import lombok.Data;

@Data
public class createcommentRequest {

	private Long issueId;
	
	private String content;
	
}
