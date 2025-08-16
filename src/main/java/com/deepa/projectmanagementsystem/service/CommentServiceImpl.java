package com.deepa.projectmanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.Comments;
import com.deepa.projectmanagementsystem.model.Issue;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.repository.CommentRepository;
import com.deepa.projectmanagementsystem.repository.IssueRepository;
import com.deepa.projectmanagementsystem.repository.UserRepository;

@Service
public class CommentServiceImpl  implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public Comments createComment(Long issueId, Long userId, String content) throws Exception{
		Optional<Issue> issueOptional=issueRepository.findById(issueId);
		Optional<User> userOptional=userRepository.findById(userId);
		
		if(issueOptional.isEmpty()) {
 			throw new Exception("issue not found with is"+issueId);
		}
		if(userOptional.isEmpty()) {
			throw new Exception("user not found with id"+userId);
		}
		Issue issue=issueOptional.get();
		User user=userOptional.get();
		
		Comments comment1=new Comments();
		comment1.setIssue(issue);
		comment1.setUser(user);
		comment1.setCreatedDateTime(LocalDateTime.now());
		comment1.setContent(content);
		//comment1.setContent(comment);
		
		Comments savedComment=commentRepository.save(comment1);
		
		issue.getComments().add(savedComment);
		
		return savedComment;
	}

	@Override
	public void deleteComment(Long commentId, Long userId)throws Exception {
		Optional<Comments> commentOptional=commentRepository.findById(commentId);
		Optional<User> userOptional=userRepository.findById(userId);
		
		if(commentOptional.isEmpty()) {
			throw new Exception("comment not found with id"+commentId);
			
		}
		if(userOptional.isEmpty()) {
			throw new Exception("user not found with id"+userId);
		}
		Comments comment=commentOptional.get();
		User user=userOptional.get();
		if(comment.getUser().equals(user)) {
			commentRepository.delete(comment);
		}
		else {
			throw new Exception("user does not have permission to delete this comment");
		}
		
	}

	@Override
	public List<Comments> findCommentByIssueId(Long issueId) {
	
		return commentRepository.findCommentsByIssueId(issueId);
	}

}
