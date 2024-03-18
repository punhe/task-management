package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.dto.request.RegisterRequestDto;
import com.se4f7.prj301.dto.response.UserResponseDto;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		try {
			UserResponseDto u = authService.getUserInfo(username);
			if (u == null) {
				boolean isSuccess = authService
						.register(new RegisterRequestDto(username, password, lastName, firstName));
				if (isSuccess) {
					request.setAttribute("msg1", "Registered Success - Please login again!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					request.setAttribute("msg2", "Register Fail!");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg3", "Account already exists!!");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
