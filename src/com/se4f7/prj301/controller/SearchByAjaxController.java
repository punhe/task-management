package com.se4f7.prj301.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.service.ToDoService;
import com.se4f7.prj301.service.impl.ToDoServiceImpl;

public class SearchByAjaxController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private ToDoService toDoService;

	public void init() {
		toDoService = new ToDoServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("vi", "VN"));
		Date date = new Date();
		String currentDate = formatter.format(date);

		String search = request.getParameter("txt");

		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userName = cookie.getValue();
					break;
				}
			}
		}

		List<ToDoEntity> list = toDoService.getWorkByNameU(search, userName);

		try (PrintWriter out = response.getWriter()) {
			for (ToDoEntity toDo : list) {
				out.println("<tr>\r\n" + "                                <td>" + toDo.getName() + "</td>\r\n"
						+ "                                <td>" + toDo.getDescription() + "</td>\r\n"
						+ "                                <td>\r\n");
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

				out.println("</td>\r\n" + "                                <td >\r\n"
						+ "                                    " + toDo.getUpdatedBy() + "\r\n"
						+ "                                </td>\r\n" + "                                <td>\r\n"
						+ "                                    " + toDo.getUpdatedDate() + "\r\n"
						+ "                                </td>\r\n" + "                                <td>\r\n");
				if (toDo.getPriority() == 0) {
					out.println("<span class=\"badge bg-info rounded-pill d-inline text-light\">Medium</span>\r\n");
				}
				if (toDo.getPriority() == 1) {
					out.println("<span class=\"badge bg-danger rounded-pill d-inline text-light\">High</span>\r\n");
				}

				out.println("</td>\r\n" + "<td>\r\n");

				if (toDo.getStatus() != 3) {
					if (toDo.getDue().compareTo(currentDate) <= 0) {
						out.println("<span class=\"text-danger\">Overdue: " + toDo.getDue() + "</span>\r\n");
					} else {
						out.println("<span>Due: " + toDo.getDue() + "</span>\r\n");
					}
				} else {
					out.println("<span class=\"badge bg-success rounded-pill d-inline text-light\">Done</span>\r\n");
				}

				out.println("<td>\r\n" + "                                    <a href=\"./edit?id=" + toDo.getId()
						+ "\" class=\"settings\" title=\"Settings\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE8B8;</i></a>\r\n"
						+ "                                    <a href=\"#\" class=\"delete\" title=\"Delete Todo\" onclick=\"handleDelete("
						+ toDo.getId() + ")\"><i class=\"material-icons\">&#xE5C9;</i></a>\r\n"
						+ "                                    <a href=\"./view-cmt?taskId=" + toDo.getId()
						+ "\" class=\"details\" title=\"View Details\" data-toggle=\"tooltip\"><i class=\"material-icons\">&#xE8F4;</i></a>\r\n"
						+ "                                </td>\r\n" + "                            "
						+ "									</tr>");

			}
		}
	}
}
