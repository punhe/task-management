package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckOTPController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		String token = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}
		if (token != null) {
			String enteredOTP = request.getParameter("otp");
			if (enteredOTP != null && enteredOTP.equals(token)) {
				request.setAttribute("msg2", "Valid OTP!!Please input password new for account!");
				request.getRequestDispatcher("reset-password.jsp").forward(request, response);
			} else {
				request.setAttribute("msg1", "Invalid OTP!!");
				request.getRequestDispatcher("checkotp.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("msg2", "Token invalid! Please send token new!");
			request.getRequestDispatcher("checkotp.jsp").forward(request, response);
		}

	}

}
