package com.se4f7.prj301.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;
import com.se4f7.prj301.utils.MailMessage;

public class AddController extends HttpServlet {

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
		request.getRequestDispatcher("add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

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
		int userRole = (int) authService.getUserRole(userName);
		String created = null;
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		if (userRole == 2) {
			int createdByI = Integer.parseInt(request.getParameter("createdBy"));
			created = authService.getUser(createdByI);
		}
		String createdByS = request.getParameter("created");
		String updatedBy = request.getParameter("updated");
		String updatedDate = request.getParameter("updated-date");
		int status = Integer.parseInt(request.getParameter("status"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		String due = request.getParameter("due");

		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String createdDate = fomatter.format(date);

		if (due.compareTo(createdDate) <= 0) {
			request.setAttribute("msg", "The due date must be greater than the current date!");
			request.getRequestDispatcher("add.jsp").forward(request, response);
		} else {
			if (created != null) {
				String emailSubject = "New Task Assigned";
				String emailMessage = "Dear User, a new task has been assigned to you. Please log in to our website for more details.";
				MailMessage.sendMessage(created, emailSubject, emailMessage);
			}
			String createdBy = (created != null) ? created : createdByS;
			boolean inserted = toDoService.create(name, description, status, createdBy, updatedBy, createdDate,
					updatedDate, priority, due);
			if (inserted == true) {
				if (userRole == 2) {
					response.sendRedirect("./load-data");
				} else {
					response.sendRedirect("./load-data-user");
				}
			} else {
				request.setAttribute("msg", "Don't added task!!");
				request.getRequestDispatcher("add.jsp").forward(request, response);
			}
		}
	}
}
