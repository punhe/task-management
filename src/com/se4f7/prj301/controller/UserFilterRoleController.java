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

public class UserFilterRoleController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String statusString = request.getParameter("role");
		int role = Integer.parseInt(statusString);

		if (role == 0) {
			response.sendRedirect("./user");
		} else {
			try {
				List<UserEntity> listRole = authService.getFilterRole(role);
				request.setAttribute("list", listRole);
				request.getRequestDispatcher("user-manager-filter.jsp").forward(request, response);

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

}
