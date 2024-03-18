package com.se4f7.prj301.entities;

public class CommentEntity {

	private int id;
	private String text;
	private int adminId;
	private int taskId;
	private String user;
	private String createdDate;

	public CommentEntity() {
	}

	public CommentEntity(int id, String text, int adminId, int taskId, String user, String createdDate) {
		this.id = id;
		this.text = text;
		this.adminId = adminId;
		this.taskId = taskId;
		this.user = user;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
