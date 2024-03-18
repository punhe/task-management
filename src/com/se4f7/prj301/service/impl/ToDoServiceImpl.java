package com.se4f7.prj301.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.repository.ToDoRepository;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.utils.ReadFromExcelFile;

public class ToDoServiceImpl implements ToDoService {

	private ToDoRepository toDo = new ToDoRepository();

	public boolean create(String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int priority, String due) {
		boolean result = false;
		try {
			this.toDo.insertWork(name, description, status, createdBy, updatedBy, createdDate, updatedDate, priority,
					due);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean update(int id, String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int priority, String due) {
		boolean result = false;
		try {
			this.toDo.updateWork(id, name, description, status, createdBy, updatedBy, createdDate, updatedDate,
					priority, due);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean delete(String id) {
		boolean result = false;
		try {
			this.toDo.deleteWork(id);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public ToDoEntity getWorkById(String id) {
		ToDoEntity toDo = new ToDoEntity();
		try {
			toDo = this.toDo.getWorkById(id);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return toDo;
	}

	public int count() {
		int result = 0;
		try {
			result = this.toDo.countToDo();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public int countByUser(String userName) {
		int result = 0;
		try {
			result = this.toDo.countUserWork(userName);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public String getDueById(int id) {
		String toDoE = null;
		try {
			toDoE = toDo.getDueById(id);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return toDoE;
	}

	public String getCreatedById(int id) {
		String toDoE = null;
		try {
			toDoE = toDo.getCreatedById(id);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return toDoE;
	}

	public List<ToDoEntity> getWorkByDue(String due) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = toDo.getWorkByDue(due);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getWorkByDue(String due, String username) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = toDo.getWorkByDue(due, username);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getAllTodo() {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = this.toDo.getAllWork();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getAllTodoLimit(int page) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = this.toDo.getAllWorkLimit(page);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getAllWorkUser(int page, String userName) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = this.toDo.getAllWorkUser(page, userName);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getFilter(int status) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = this.toDo.getFilter(status);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getFilterUser(int status, String username) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = this.toDo.getFilterUser(status, username);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getPriorityUser(int priority, String username) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = this.toDo.getPriorityUser(priority, username);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getWorkByName(String name) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = toDo.getWorkByName(name);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getWorkByNameU(String name, String createdBy) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = toDo.getWorkByNameU(name, createdBy);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getWorkByNameToExcel(String createdBy) {
		List<ToDoEntity> list = new ArrayList<>();
		try {
			list = this.toDo.getWorkByNameToExcel(createdBy);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public boolean importFromExcel(HttpServletRequest request, HttpServletResponse response, String filePath) {
		try {
			ReadFromExcelFile.importExcel(request, response, filePath);
			return true;
		} catch (Exception e) {
			System.out.println("Error importing from Excel: " + e.getMessage());
			return false;
		}
	}

}
