package com.se4f7.prj301.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;

public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;

	public void init() {
		toDoService = new ToDoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");

		List<ToDoEntity> list = toDoService.getWorkByName(search);
		int count = toDoService.count();

		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
