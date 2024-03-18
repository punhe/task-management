package com.se4f7.prj301.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.UserEntity;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;

public class UserFilterController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String statusString = request.getParameter("status");
		int status = Integer.parseInt(statusString);

		if (status == 0) {
			response.sendRedirect("./user");
		} else {
			try {
				List<UserEntity> listStatus = authService.getFilterStatus(status);
				request.setAttribute("list", listStatus);
				request.getRequestDispatcher("user-manager-filter.jsp").forward(request, response);

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

}
