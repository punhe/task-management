package com.se4f7.prj301.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.dto.response.UserResponseDto;
import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;

public class LoadFilterDueController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;

	public void init() {
		toDoService = new ToDoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String due = request.getParameter("due");
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
		AuthService authService = new AuthServiceImpl();
		UserResponseDto u = authService.getUserInfo(userName);
		int userRole = u.getRole();

		if (due.equalsIgnoreCase("all")) {
			if (userRole == 2) {
				response.sendRedirect("./load-data");
			} else {
				response.sendRedirect("./load-data-user");
			}
		} else {
			try {
				List<ToDoEntity> list = toDoService.getWorkByDue(due);
				List<ToDoEntity> listU = toDoService.getWorkByDue(due, userName);
				request.setAttribute("list", list);
				request.setAttribute("listU", listU);
				request.getRequestDispatcher("admin-filter.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
