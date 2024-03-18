package com.se4f7.prj301.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se4f7.prj301.dto.response.UserResponseDto;
import com.se4f7.prj301.service.AuthService;
import com.se4f7.prj301.service.impl.AuthServiceImpl;

public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 2860215007883522580L;

    private AuthService authService;

    public void init() {
        authService = new AuthServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");
        UserResponseDto u = authService.getUserInfo(userName);

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        boolean updated = authService.update(u.getUsername(), firstName, lastName);
        if (updated == true) {
            request.setAttribute("msg", "Updated profile success!!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            request.setAttribute("msg1", "Updated profile fail!!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
		}

	}

}
