package com.se4f7.prj301.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;

public class SearchByAjaxAdController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;

	public void init() {
		toDoService = new ToDoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String search = request.getParameter("txt");

		List<ToDoEntity> list = toDoService.getWorkByName(search);
		try (PrintWriter out = response.getWriter()) {
			for (ToDoEntity toDo : list) {
				out.println("<tr>");
				out.println("    <td>" + toDo.getName() + "</td>");
				out.println("    <td>");
				if (toDo.getStatus() == 0) {
					out.println("<span class=\"badge bg-danger rounded-pill d-inline text-light\">Reject</span>\r\n");
				}
				if (toDo.getStatus() == 1) {
					out.println("<span class=\"badge bg-warning rounded-pill d-inline text-light\">To Do</span>\r\n");
				}
				if (toDo.getStatus() == 2) {
					out.println(
							"<span class=\"badge bg-primary rounded-pill d-inline text-light\">In Progress</span>\r\n");
				}
				if (toDo.getStatus() == 3) {
					out.println("<span class=\"badge bg-success rounded-pill d-inline text-light\">Done</span>\r\n");
				}
				out.println("    </td>");
				out.println("    <td>" + toDo.getCreatedBy() + "</td>");
				out.println("    <td>" + toDo.getUpdatedBy() + "</td>");
				out.println("    <td>" + toDo.getCreatedDate() + "</td>");
				out.println("    <td>" + toDo.getUpdatedDate() + "</td>");
				out.println("    <td>");

				if (toDo.getStatus() != 3) {
					out.println(toDo.getDue());
				} else {
					out.println("<span class=\"badge bg-success rounded-pill d-inline text-light\">Done</span>");
				}

				out.println("    </td>");
				out.println("    <td>");

				if (toDo.getStatus() != 3) {
					out.println("<a href=\"./edit?id=" + toDo.getId()
							+ "\" class=\"settings\" title=\"Settings\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE8B8;</i></a>");
					out.println("<a href=\"#\" class=\"delete\" onclick=\"handleDelete(" + toDo.getId()
							+ ")\"><i class=\"material-icons\">&#xE5C9;</i></a>");
					out.println("<a href=\"./time-out?id=" + toDo.getId()
							+ "\" class=\"due text-warning\" title=\\\"Report due\\\" ><i class=\"material-icons\">alarm_off</i></a>");
					out.println("<a href=\"./comments?taskId=" + toDo.getId()
							+ "\" class=\"details\" title=\"View Details\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE8F4;</i></a>");
				} else {
					out.println("<a href=\"./edit?id=" + toDo.getId()
							+ "\" class=\"settings\" title=\"Settings\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE8B8;</i></a>");
					out.println("<a href=\"#\" class=\"delete\" onclick=\"handleDelete(" + toDo.getId()
							+ ")\"><i class=\"material-icons\">&#xE5C9;</i></a>");
					out.println("<a href=\"./comments?taskId=" + toDo.getId()
							+ "\" class=\"details\" title=\"View Details\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE8F4;</i></a>");
				}

				out.println("    </td>");
				out.println("</tr>");

			}

		}
	}
}
