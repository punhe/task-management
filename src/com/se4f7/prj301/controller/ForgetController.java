package com.se4f7.prj301.controller;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.dto.response.UserResponseDto;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;
import com.se4f7.prj301.utils.MailMessage;

public class ForgetController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("forget.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");

		try {
			UserResponseDto u = authService.getUserInfo(userName);
			if (u != null) {
				String characters = "0123456789";
				StringBuilder otp = new StringBuilder();
				SecureRandom random = new SecureRandom();

				for (int i = 0; i < 4; i++) {
					int index = random.nextInt(characters.length());
					otp.append(characters.charAt(index));
				}
				String token = otp.toString();

				Cookie otpCookie = new Cookie("token", token);
				Cookie uCookie = new Cookie("username", userName);
				otpCookie.setMaxAge(60);
				uCookie.setMaxAge(60);

				response.addCookie(otpCookie);
				response.addCookie(uCookie);
				MailMessage.sendToken(userName, token);
				request.setAttribute("msg", "OTP sent to your email!");
				request.getRequestDispatcher("checkotp.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "Username invalid!!");
				request.getRequestDispatcher("forget.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
