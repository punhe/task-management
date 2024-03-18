package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.CommentService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;
import com.se4f7.prj301.service.impl.CommentServiceImpl;

public class DeleteCMTController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;
	private CommentService commentService;

	public void init() {
		authService = new AuthServiceImpl();
		commentService = new CommentServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String taskId = request.getParameter("taskId");

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

		boolean deleted = commentService.delete(id);
		if (deleted == true) {
			if (userRole == 2) {
				response.sendRedirect("./comments?taskId=" + taskId);
			} else {
				response.sendRedirect("./view-cmt?taskId=" + taskId);
			}
		}
	}
}
