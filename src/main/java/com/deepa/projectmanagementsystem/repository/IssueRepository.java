package com.deepa.projectmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepa.projectmanagementsystem.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long>{
	
//public List<Issue> findByProjectID(Long projectid);
public List<Issue> findByProject_Id(Long projectid); 


}
