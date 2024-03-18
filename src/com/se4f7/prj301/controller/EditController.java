package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;
import com.se4f7.prj301.utils.MailMessage;

public class EditController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;
	private AuthService authService;

	public void init() {
		toDoService = new ToDoServiceImpl();
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		ToDoEntity toDo = toDoService.getWorkById(id);
		request.setAttribute("work", toDo);
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		ToDoEntity toDo = toDoService.getWorkById(id);
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String createdBy = request.getParameter("created");
		String updatedBy = request.getParameter("updated");
		String updatedDate = request.getParameter("updated-date");
		int status = Integer.parseInt(request.getParameter("status"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		String due = request.getParameter("due");

		String createdDate = toDo.getCreatedDate();

		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userName = cookie.getValue();
					break;
				}
			}
		}

		if (due.compareTo(updatedDate) <= 0) {
			ToDoEntity toDoU = toDoService.getWorkById(id);
			request.setAttribute("msg", "The due date must be greater than the current date!");
			request.setAttribute("work", toDoU);
			request.getRequestDispatcher("update.jsp").forward(request, response);
		} else {

			int userRole = (int) authService.getUserRole(userName);

			boolean updated = toDoService.update(toDo.getId(), name, description, status, createdBy, updatedBy,
					createdDate, updatedDate, priority, due);
			if (status == 3) {
				String adminEmail = "sangtnqe170193@fpt.edu.vn";
				String emailSubject = "Task Completed Notification";
				String emailMessage = "User " + userName + " has completed the task with ID " + id;
				MailMessage.sendMessage(adminEmail, emailSubject, emailMessage);
			} else if (status == 0 && userRole == 2) {
				String emailSubject = "Task Rejected Notification";
				String emailMessage = "Your task with ID " + id
						+ " has been rejected by the admin. Please read the comments for details.";
				MailMessage.sendMessage(createdBy, emailSubject, emailMessage);
			}
			if (updated == true) {
				if (userRole == 2) {
					response.sendRedirect("./load-data");
				} else {
					response.sendRedirect("./load-data-user");
				}
			} else {
				response.sendRedirect("./edit?id=" + id);
			}
		}
	}
}
