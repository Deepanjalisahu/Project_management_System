package com.deepa.projectmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.Issue;
import com.deepa.projectmanagementsystem.model.Project;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.repository.IssueRepository;
import com.deepa.projectmanagementsystem.request.IssueRequest;

@Service
public class IssueServiceimpl implements IssueService{

	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Issue getIssueById(Long issueId) throws Exception {
		Optional<Issue> issue = issueRepository.findById(issueId);
		if(issue.isPresent()) {
			return issue.get();
		}
		throw new Exception("No issue found with issue"+issueId);
	}

	@Override
	public List<Issue> getIssueByProject(Long projectId) throws Exception {
		return issueRepository.findByProject_Id(projectId);
	}

	@Override
	public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
		Project project=projectService.getProjectById(issueRequest.getProjectid());
		Issue issue =new Issue();
		issue.setTitle(issueRequest.getTitle());
		issue.setDescription(issueRequest.getDescription());
		issue.setStatus(issueRequest.getStatus());
		issue.setPriority(issueRequest.getPriority());
		issue.setProjectID(issueRequest.getProjectid());
		issue.setDueDate(issueRequest.getDueDate());
		
		issue.setProject(project);
		
		return issueRepository.save(issue);
	}

	@Override
	public void deleteIssue(Long issueId, Long userid) throws Exception {
		getIssueById(issueId);
	    issueRepository.deleteById(issueId);;
	}

	@Override
	public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
		User user=userService.findUserbyId(userId);
		Issue issue=getIssueById(issueId);
		issue.setAssignee(user);
		
		return issueRepository.save(issue);
	}

	@Override
	public Issue updatedStatus(Long issueId, String status) throws Exception {
		Issue issue=getIssueById(issueId);
		issue.setStatus(status);
		return issueRepository.save(issue);
	}

	@Override
	public Issue createIssue(IssueRequest issue, Long long1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	


}
