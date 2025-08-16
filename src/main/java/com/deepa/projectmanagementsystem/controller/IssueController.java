package com.deepa.projectmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepa.projectmanagementsystem.DTO.IssueDTO;
import com.deepa.projectmanagementsystem.model.Issue;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.request.IssueRequest;
import com.deepa.projectmanagementsystem.response.AuthResponse;
import com.deepa.projectmanagementsystem.response.MessageResponse;
import com.deepa.projectmanagementsystem.service.IssueService;
import com.deepa.projectmanagementsystem.service.UserService;

@RestController
@RequestMapping("/api/isuue")
public class IssueController {
     
	@Autowired
	private UserService userservice;
	
	@Autowired
	private IssueService issueService;
	
	@GetMapping("/{issueId}")
	public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception{
		return  ResponseEntity.ok(issueService.getIssueById(issueId));
	}
	@GetMapping("/projects/{projectId}")
	public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) 
	           throws Exception{
		return ResponseEntity.ok(issueService.getIssueByProject(projectId));
		
	}
	@PostMapping("/update")
	public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,@RequestHeader("Authorization") 
	               String jwt) throws Exception {
    
    User tokenUser=userservice.findUserProfileByjwt(jwt);
    User user=userservice.findUserbyId(tokenUser.getId());
    
    if(user !=null) {
    	Issue createdIssue=issueService.createIssue(issue, tokenUser);
    	IssueDTO issueDTO=new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setDuaDate(createdIssue.getDueDate());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setPrioriry(createdIssue.getPriority());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setProjectId(createdIssue.getProjectID());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setAssignee(createdIssue.getAssignee());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setTitle(createdIssue.getTitle());
    
	return ResponseEntity.ok(issueDTO);
	}
	return null;
	}
    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,@RequestHeader("Authorization") String token)
                throws Exception{
    	User user=userservice.findUserProfileByjwt(token);
    	issueService.deleteIssue(issueId, user.getId());
    	
    	MessageResponse res=new MessageResponse();
    	res.setMeassage("issue deleted");
    	
    	return ResponseEntity.ok(res);
	}
    @PutMapping("/{issueId}/assigne/{userid}")
    public RequestBody addUserIssue(@PathVariable Long issueid,@PathVariable Long userId)
             throws Exception{
    	Issue issue=issueService.addUserToIssue(issueid, userId);
    	return (RequestBody) ResponseEntity.ok(issue);
    }
    @PutMapping("{issueId}/status/{status}")
    public ResponseEntity<Issue> updatedIssueStatus(
    		@PathVariable String status,
    		@PathVariable Long issueId) throws Exception{
    	Issue issue =issueService.updatedStatus(issueId, status);
    	return ResponseEntity.ok(issue);
    }
    
	
}
