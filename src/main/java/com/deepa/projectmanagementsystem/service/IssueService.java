package com.deepa.projectmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.Issue;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.request.IssueRequest;

@Service
public interface IssueService {
	
	Issue  getIssueById(Long issueId) throws Exception;
	
	List<Issue> getIssueByProject(Long projectId) throws Exception;
	
    Issue createIssue(IssueRequest issue,Long long1) throws Exception;
  
   // Optional<Issue> updateIssue(Long issueid,IssueRequest updatedIssue,Long userid) throws Exception;
    
   void deleteIssue(Long issueId,Long userid) throws Exception;
   
  // List<Issue> getIssuesByAssignedId(Long assignedId) throws IssueException;
   
   //List<Issue> searchIssues(String title,String status,String prioriry,Long assignedId) throws IssueException;
   
  // List<User> getAssinedforIssue(Long issueId) throws IssueException;
   
   Issue addUserToIssue(Long issueId,Long userId)throws Exception;
   
   Issue updatedStatus(Long issueId,String status)throws Exception;

   Issue createIssue(IssueRequest issueRequest, User user) throws Exception;
   
}
