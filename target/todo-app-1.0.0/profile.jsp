<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.se4f7.prj301.service.AuthService"%>
<%@page import="com.se4f7.prj301.service.impl.AuthServiceImpl"%>
<%@page import="com.se4f7.prj301.dto.response.UserResponseDto"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Profile</title>
        <%@include file="./header.jsp" %>
    </head>

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

        AuthService authService = new AuthServiceImpl();
        UserResponseDto u = authService.getUserInfo(user);
        int userRole = u.getRole();
        String roleText = "";
        if (userRole == 1) {
            roleText = "Guest";
        } else if (userRole == 2) {
            roleText = "Admin";
        }
    %>

    <body>
        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                             class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <form action="./edit-profile" id="form-profile">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Profile</span>
                            </div>

                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">User Information</h5>
                            <p style="color: blue; text-align: left;">
                                ${msg}
                            </p>
                            <p style="color: red; text-align: left;">
                                ${msg1}
                            </p>

                            <!-- Username -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="username">Email</label>
                                <input id="username" class="form-control form-control-lg" name="username" value="<%= user%>" readonly />
                            </div>

                            <div class="form-group mb-4">
                                <label class="form-label" for="role">Role</label>
                                    <input id="role" class="form-control form-control-lg" name="role" value="<%= roleText%>" readonly />
                            </div>
                            <!-- Firstname -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="firstname">First Name</label>
                                <input id="firstname" class="form-control form-control-lg" name="firstname" value="<%= u.getFirstName()%>" rules="required" />
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <!-- Lastname -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="lastname">Last Name</label>
                                <input id="lastname" class="form-control form-control-lg" name="lastname" value="<%= u.getLastName()%>" rules="required"/>
                                <span class="form-message" style="color: red"></span>
                            </div>
                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Change Profile</button>
                                <% if (userRole == 2) { %>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't want to change profile? <a href="load-data"
                                                                                                         class="link-danger">Go back</a></p>
                                    <% } else { %>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't want to change profile? <a href="load-data-user"
                                                                                                         class="link-danger">Go back</a></p>
                                    <%  }%>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%@include file="./footer.jsp" %>
        </section>
        <%@include file="./JS.jsp" %>
        <script>
            Validator("#form-profile");
        </script>
    </body>

</html>
