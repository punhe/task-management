package com.se4f7.prj301.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class DeleteController extends HttpServlet {

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
		String id = request.getParameter("id");
		ToDoEntity toDo = toDoService.getWorkById(id);
		String createdBy = toDo.getCreatedBy();
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
		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String dateDelete = fomatter.format(date);
		int userRole = (int) authService.getUserRole(userName);

		boolean deleted = toDoService.delete(id);
		if (deleted == true) {
			if (userRole == 2) {
				String reason = request.getParameter("reason");
				String emailSubject = "Task Deleted Notification";
				String emailMessage = "Your task with ID " + id + " has been deleted by the admin. <span style='color: red;'> Reason: "
	                    + reason + " </span>. Deletion Time: " + dateDelete + ".";
				MailMessage.sendMessage(createdBy, emailSubject, emailMessage);
				response.sendRedirect("./load-data");
			} else {
				response.sendRedirect("./load-data-user");
			}
		} else {
			response.sendRedirect("./delete?id=" + id);
		}
	}
}
