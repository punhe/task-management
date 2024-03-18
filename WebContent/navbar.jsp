<%@page import="com.se4f7.prj301.service.AuthService"%>
<%@page import="com.se4f7.prj301.service.impl.AuthServiceImpl"%>
<%@page import="com.se4f7.prj301.dto.response.UserResponseDto"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <%
        String user = (String) session.getAttribute("user");
        String userName = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionID = cookie.getValue();
                }
            }
        } else {
            response.sendRedirect("error.jsp");
        }

    %>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                &nbsp;&nbsp;&nbsp;
                <a href="index.jsp" style="color: black"><i class="fa fa-home" style="margin-top: 5px; font-size: 20px" aria-hidden="true"></i></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp"> <h6>Home</h6></a>
            </li>
            <%                if (user != null) {
                    AuthService authService = new AuthServiceImpl();
                    UserResponseDto u = authService.getUserInfo(user);
                    int userRole = u.getRole();
            %>
            <li class="nav-item active">
                <a class="nav-link"
                   <%
                       if (userRole == 2) {
                   %>
                   href="./load-data"
                   <%  } else {%>
                   href="./load-data-user"
                   <% } %>
                   ><h6 style="margin-right: 1150px">Manager</h6></a>
            </li>
            <% } %>
        </ul>
        <div class="form-inline my-2 my-lg-0">
            <% if (user != null) {%>
            <a href="profile.jsp" style="text-decoration: none; color: #000; margin-top: 10px"><p>Welcome, <%= user%></p></a>
            &nbsp;
            <a href="./logout" style="text-decoration: none; color: #000">
                <i class="fa fa-fw fa-sign-out-alt text-dark mr-3" style="margin-bottom: 10px"></i>
            </a>
            <% } else {%>
            <button class="btn btn-outline-success my-2 my-sm-0" onclick="handleToLogin()">Login</button>
            &nbsp;&nbsp;&nbsp;
            <button class="btn btn-outline-success my-2 my-sm-0" onclick="handleToRegister()">Register</button>
            &nbsp;&nbsp;&nbsp;
            <% }%>
        </div>
    </div>
</nav>


