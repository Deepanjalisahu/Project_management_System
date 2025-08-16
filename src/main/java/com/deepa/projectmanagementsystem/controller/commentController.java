package com.deepa.projectmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepa.projectmanagementsystem.model.Comments;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.request.createcommentRequest;
import com.deepa.projectmanagementsystem.response.MessageResponse;
import com.deepa.projectmanagementsystem.service.CommentService;
import com.deepa.projectmanagementsystem.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class commentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<Comments> createComment(
			@RequestBody createcommentRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user =userService.findUserProfileByjwt(jwt);
		Comments createdcomment=commentService.createComment(
				req.getIssueId(), 
				user.getId(),
				req.getContent());
		return new ResponseEntity<>(createdcomment,HttpStatus.CREATED);
	}
	
@DeleteMapping("/{commentId}")
public ResponseEntity<MessageResponse>deleteComment(@PathVariable Long commentId,
		@RequestHeader("Authorization") String jwt)throws Exception{
	User user =userService.findUserProfileByjwt(jwt);
	commentService.deleteComment(commentId, user.getId());
	MessageResponse res = new MessageResponse();
	res.setMeassage("comment deleted successfully");
	return new ResponseEntity<>(res,HttpStatus.OK);
}
@GetMapping("{issueId}")
public ResponseEntity<Comments> getCommentByissueId(@PathVariable Long issueId){
	List<Comments> comments=commentService.findCommentByIssueId(issueId);
	return new ResponseEntity<>(HttpStatus.OK);
}
			
			
}
