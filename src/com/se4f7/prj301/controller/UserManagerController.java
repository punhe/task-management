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

public class UserManagerController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String index = request.getParameter("page");
		if (index == null) {
			index = "1";
		}
		int page = Integer.parseInt(index);
		try {
			int count = authService.count();
			int endP = count / 5;
			if (count % 5 != 0) {
				endP++;
			}

			List<UserEntity> list = authService.getAllUser(page);
			request.setAttribute("list", list);
			request.setAttribute("endP", endP);
			request.setAttribute("tag", page);
			request.setAttribute("count", count);
			request.getRequestDispatcher("user-manager.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
