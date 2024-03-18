package com.se4f7.prj301.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.entities.UserEntity;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;

public class SearchUByAjaxController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	private AuthService authService;

	public void init() {
		authService = new AuthServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String search = request.getParameter("txt");

		List<UserEntity> list = authService.searchUserByName(search);

		try (PrintWriter out = response.getWriter()) {
			for (UserEntity u : list) {
				out.println("<tr>");
				out.println("    <td>" + u.getUsername() + "</td>");
				out.println("    <td>");
				if (u.getStatus() == 1) {
					out.println("<span class=\"badge bg-success rounded-pill d-inline text-light\">Active</span>\r\n");
				}
				if (u.getStatus() == 2) {
					out.println("<span class=\"badge bg-danger rounded-pill d-inline text-light\">Inactive</span>\r\n");
				}
				out.println("    </td>");
				out.println("    <td>" + u.getFirstName() + "</td>");
				out.println("    <td>" + u.getLastName() + "</td>");
				out.println("    <td>");
				if (u.getRole() == 1) {
					out.println("<span class=\"badge bg-info rounded-pill d-inline text-light\">Guest</span>\r\n");
				}
				if (u.getRole() == 2) {
					out.println("<span class=\"badge bg-primary rounded-pill d-inline text-light\">Admin</span>\r\n");
				}
				out.println("    </td>");
				out.println("    <td>");
				if (u.getRole() == 1) {
					out.println("        <a href=\"./edit-user?id=" + u.getId()
							+ "\" class=\"settings\" title=\"Settings\" data-toggle=\"tooltip\">");
					out.println("            <i class=\"material-icons\">&#xE8B8;</i>");
					out.println("        </a>");
				} else {
					out.println(
							"        <a href=\"error.jsp\" class=\"settings\" title=\"Settings\" data-toggle=\"tooltip\">");
					out.println("            <i class=\"material-icons text-danger\">block</i>");
					out.println("        </a>");
				}

				out.println("    </td>");
				out.println("</tr>");
			}
		}
	}

}
