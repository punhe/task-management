package com.se4f7.prj301.entities;

public class ToDoEntity extends BaseEntity {

	private String name;

	private String description;

	private int status;

	private int priority;

	private String due;

	public ToDoEntity(int id, String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int priority, String due) {
		super(id, createdBy, updatedBy, createdDate, updatedDate);
		this.name = name;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.due = due;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public ToDoEntity() {
		super();
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ToDoEntity{" + "name=" + name + ", description=" + description + ", status=" + status + ", createBy="
				+ createdBy + '}';
	}

}
