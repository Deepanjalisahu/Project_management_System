package com.deepa.projectmanagementsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deepa.projectmanagementsystem.model.Comments;

@Service
public interface CommentService {

	Comments createComment(Long issueId,Long userId,String comment) throws Exception;
	
	void deleteComment(Long commentId,Long userId) throws Exception;
	
	List<Comments> findCommentByIssueId(Long issueId);
}
