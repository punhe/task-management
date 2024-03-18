package com.se4f7.prj301.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.ToDoEntity;

public interface ToDoService {

	public boolean create(String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int priority, String due);

	public boolean update(int id, String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int priority, String due);

	public boolean delete(String id);

	public ToDoEntity getWorkById(String id);

	public int count();

	public int countByUser(String userName);

	public String getDueById(int id);

	public String getCreatedById(int id);

	public List<ToDoEntity> getWorkByDue(String due);

	public List<ToDoEntity> getWorkByDue(String due, String username);

	public List<ToDoEntity> getAllTodo();

	public List<ToDoEntity> getAllTodoLimit(int page);

	public List<ToDoEntity> getAllWorkUser(int page, String userName);

	public List<ToDoEntity> getFilter(int status);

	public List<ToDoEntity> getFilterUser(int status, String username);

	public List<ToDoEntity> getPriorityUser(int priority, String username);

	public List<ToDoEntity> getWorkByName(String name);

	public List<ToDoEntity> getWorkByNameU(String name, String createdBy);

	public List<ToDoEntity> getWorkByNameToExcel(String createdBy);

	public boolean importFromExcel(HttpServletRequest request, HttpServletResponse response, String filePath);

}
