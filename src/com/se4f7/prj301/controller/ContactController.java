package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.utils.MailMessage;

public class ContactController extends HttpServlet {

	private static final long serialVersionUID = 2860215007883522580L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String name = request.getParameter("fullName");
		String email = request.getParameter("email");
		String message = request.getParameter("message");

		String htmlTextMessage = "<html>" + "<head><meta charset=\"UTF-8\"></head>" + "<body>"
				+ "<h2 style='color:green;'>Message to TaskList Admin</h2>" + "Contact !!<br/><br/> Name: " + name + ","
				+ "<br/><br/> Email Id: " + email + "<br><br/>" + "Message: " + "<span style='color:grey;'>" + message
				+ "</span>"
				+ "<br/><br/>We are glad that fans are choosing us! <br/><br/>Thanks & Regards<br/><br/>Auto Generated Mail"
				+ "</body>" + "</html>";
		String messages = MailMessage.sendMessage("sangtnqe170193@fpt.edu.vn",
				"Contact Message | " + name + " | " + email, htmlTextMessage);
		if ("SUCCESS".equals(messages)) {
			messages = "Comments Sent Successfully";
		} else {
			messages = "Failed: Please Configure mailer.email and password in application.properties first";
		}
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.include(request, response);
		response.getWriter().print("<script>alert('" + messages + "')</script>");
	}

}
