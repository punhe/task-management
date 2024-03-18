package com.se4f7.prj301.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.entities.CommentEntity;
import com.se4f7.prj301.repository.CommentRepository;
import com.se4f7.prj301.service.CommentService;

public class CommentServiceImpl implements CommentService {

	private CommentRepository comment = new CommentRepository();

	public boolean insertComments(String text, int adminId, int taskId, String user, String createdBy) {
		boolean result = false;
		try {
			comment.insertComments(text, adminId, taskId, user, createdBy);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public List<CommentEntity> getCommentsByTaskId(int taskId, int adminId, String user) {
		List<CommentEntity> list = new ArrayList<>();
		try {
			list = comment.getCommentsByTaskId(taskId, adminId, user);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<CommentEntity> getComments(int taskId, String user) {
		List<CommentEntity> list = new ArrayList<>();
		try {
			list = comment.getComments(taskId, user);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public boolean delete(String id) {
		boolean result = false;
		try {
			comment.deleteCmt(id);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}
}
