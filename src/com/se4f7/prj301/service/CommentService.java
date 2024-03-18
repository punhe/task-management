package com.se4f7.prj301.service;

import java.util.List;

import com.se4f7.prj301.entities.CommentEntity;

public interface CommentService {

	public boolean insertComments(String text, int adminId, int taskId, String user, String createdBy);

	public List<CommentEntity> getCommentsByTaskId(int taskId, int adminId, String user);

	public List<CommentEntity> getComments(int taskId, String user);

	public boolean delete(String id);

}
