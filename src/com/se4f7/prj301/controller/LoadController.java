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

public class LoadController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;

	public void init() {
		toDoService = new ToDoServiceImpl();
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
			int count = toDoService.count();
			int endP = count / 5;
			if (count % 5 != 0) {
				endP++;
			}
			List<ToDoEntity> list = toDoService.getAllTodoLimit(page);
			request.setAttribute("list", list);
			request.setAttribute("endP", endP);
			request.setAttribute("tag", page);
			request.setAttribute("count", count);
			request.getRequestDispatcher("admin.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
