package com.deepa.projectmanagementsystem.request;

import java.time.LocalDate;

import lombok.Data;

@Data

public class IssueRequest {

	private String title;
    private String description;
    private  String status;
    private Long projectid;
    private String priority;
    private LocalDate dueDate;
	
}
