package com.deepa.projectmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepa.projectmanagementsystem.model.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {

	List<Comments> findCommentsByIssueId(Long issueId);
}
