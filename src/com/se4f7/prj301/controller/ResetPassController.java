package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;

public class ResetPassController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName())) {
					userName = cookie.getValue();
					break;
				}
			}
		}

		if (userName == null) {
			userName = (String) request.getSession().getAttribute("username");
		}

		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("msg", "New Password and Confirm Password do not match");
			request.getRequestDispatcher("reset-password.jsp").forward(request, response);
			return;
		}

		boolean isSuccess = authService.change(userName, newPassword);
		if (isSuccess) {
			request.setAttribute("msg2", "Change password success - Please login again!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.setAttribute("msg1", "Change password fail!");
			request.getRequestDispatcher("reset-password.jsp").forward(request, response);
		}

	}

}
