package com.deepa.projectmanagementsystem.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inviterequest {

	private Long projectId;
	private String email;
	
}
