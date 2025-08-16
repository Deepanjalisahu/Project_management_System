package com.deepa.projectmanagementsystem.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.deepa.projectmanagementsystem.model.Project;
import com.deepa.projectmanagementsystem.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {

	private Long id;
	private String title;
	private String description;
	private String status;
	private Long projectId;
	private String prioriry;
	private LocalDate duaDate;
	private List<String> tags= new ArrayList<>();
	private Project project;
	private User assignee;
}
