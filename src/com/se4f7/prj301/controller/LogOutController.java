package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					loginCookie = cookie;
					break;
				}
			}
		}
		if (loginCookie != null) {
			HttpSession session = request.getSession();
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
			session.removeAttribute("user");
		}
		response.sendRedirect("index.jsp");

	}
}
