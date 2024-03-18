package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.UserEntity;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;

public class EditUserController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);

		UserEntity u = authService.getUserById(id);

		request.setAttribute("user", u);
		request.getRequestDispatcher("update-user.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		UserEntity u = authService.getUserById(id);

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		int status = Integer.parseInt(request.getParameter("status"));
		int role = Integer.parseInt(request.getParameter("role"));

		boolean updated = authService.updateUser(u.getId(), firstName, lastName, status, role);
		if (updated == true) {
			response.sendRedirect("./user");
		} else {
			response.sendRedirect("./edit-user?id=" + id);
		}
	}

}
