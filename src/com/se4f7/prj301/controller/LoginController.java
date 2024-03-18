package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.se4f7.prj301.dto.request.LoginRequestDto;
import com.se4f7.prj301.dto.response.LoginResponseDto;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");

		Cookie urem = new Cookie("u", username);
		Cookie prem = new Cookie("p", password);
		Cookie rem = new Cookie("r", remember);

		if (remember != null && remember.equalsIgnoreCase("on")) {
			urem.setMaxAge(60 * 60 * 24 * 7);
			prem.setMaxAge(60 * 60 * 24 * 7);
			rem.setMaxAge(60 * 60 * 24 * 7);
		} else {
			urem.setMaxAge(0);
			prem.setMaxAge(0);
			rem.setMaxAge(0);
		}
		response.addCookie(urem);
		response.addCookie(prem);
		response.addCookie(rem);
		HttpSession session = request.getSession();
		int userRole = (int) authService.getUserRole(username);
		int userStatus = (int) authService.getUserStatus(username);

		try {
			LoginResponseDto loginInfo = authService.login(new LoginRequestDto(username, password));
			if (userStatus != 1) {
				request.setAttribute("msg", "Account has been disabled or does not exist!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else if (loginInfo != null) {
				session.setAttribute("user", username);
				session.setAttribute("role", userRole);
				Cookie usernameC = new Cookie("user", username);
				usernameC.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(usernameC);
				if (userRole == 2) {
					response.sendRedirect("./load-data");
				} else {
					response.sendRedirect("./load-data-user");
				}
			} else {
				request.setAttribute("msg", "Wrong username or password!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
