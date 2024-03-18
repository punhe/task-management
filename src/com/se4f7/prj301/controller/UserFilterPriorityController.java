package com.se4f7.prj301.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;

public class UserFilterPriorityController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;

	public void init() {
		toDoService = new ToDoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String priorityString = request.getParameter("priority");
		int priority = Integer.parseInt(priorityString);
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
		if (priority == 2) {
			response.sendRedirect("./load-data-user");
		} else {
			try {
				List<ToDoEntity> listOfUser1 = toDoService.getPriorityUser(priority, userName);
				request.setAttribute("listP", listOfUser1);
				request.getRequestDispatcher("user-priority-filter.jsp").forward(request, response);

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
