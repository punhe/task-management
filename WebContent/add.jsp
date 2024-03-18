<%@page import="com.se4f7.prj301.service.AuthService"%>
<%@page import="com.se4f7.prj301.service.impl.AuthServiceImpl"%>
<%@page import="com.se4f7.prj301.dto.response.UserResponseDto"%>
<%@page import="com.se4f7.prj301.entities.UserEntity"%>
<%@page import="javax.servlet.http.Cookie" %>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Form</title>
        <%@include file="header.jsp" %>
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
        List<UserEntity> list = (List<UserEntity>) authService.getAllRoleUser();
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
                        <form action="./add" method="POST" id="form-add">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Add to do list</span>
                            </div>

                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg}</h6>
                            <!-- Name -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="name">Name</label>
                                <input id="name" class="form-control form-control-lg" placeholder="Enter Name of the job"
                                       name="name" rules="required|min:6" />
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <!-- Description -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="description">Description</label>
                                <input id="description" class="form-control" placeholder="Enter description of the job"
                                       name="description"/>
                            </div>

                            <!-- Status -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="status">Status</label>
                                <select id="status" class="form-select" name="status" rules="required">
                                	<option value="1">To Do</option>
                                    <option value="0">Reject</option>
                                    <option value="2">In Progress</option>
                                    <option value="3">Done</option>
                                </select>
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <!-- Priority -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="priority">Priority</label>
                                <select id="priority" class="form-select" name="priority" rules="required">
                                    <option value="0">Medium</option>
                                    <option value="1">High</option>
                                </select>
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <!-- due -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="due">Due Date</label>
                                <input type="date" id="due" class="form-control form-control-lg" name="due" rules="required" />
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <% if (userRole == 1) {%>
                            <input value="<%=user%>" name="created" type="hidden"/>
                            <% } else if (userRole == 2) { %>
                            <div class="form-group mb-4">
                                <label class="form-label" for="createdBy">Assign Task to User</label>
                                <select id="createdBy" class="form-select" name="createdBy" rules="required">
                                    <option value="-1">Choose a user....</option>
                                    <% for (UserEntity users : list) {%>
                                    <option value="<%= users.getId()%>"><%= users.getUsername()%></option>
                                    <% } %>
                                </select>
                                <span class="form-message" style="color: red"></span>
                            </div>
                            <% } %>

                            <!-- Updated By -->

                            <input value="" name="updated" type="hidden"/>

                            <!-- Updated Date -->

                            <input value="" name="updated-date" type="hidden"/>


                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Add</button>
                                <% if (userRole == 2) { %>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't want to add? <a href="load-data"
                                                                                              class="link-danger">Go back</a></p>
                                    <% } else { %>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't want to add? <a href="load-data-user"
                                                                                              class="link-danger">Go back</a></p>
                                    <%  }%>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <br> 
            <br>
            <br>
            <%@include file="./footer.jsp" %>
        </section>
        <%@include file="./JS.jsp" %>
        <script>
            Validator("#form-add");
        </script>
    </body>

</html>
