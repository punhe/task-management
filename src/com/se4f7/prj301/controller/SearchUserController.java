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

public class SearchUserController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");

		List<UserEntity> list = authService.searchUserByName(search);
		int count = authService.count();

		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("user-manager.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
