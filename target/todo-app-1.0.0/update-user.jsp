<%@page import="com.se4f7.prj301.entities.UserEntity"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Form</title>
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
        UserEntity u = (UserEntity) request.getAttribute("user");
    %>
    <body>
        <br>
        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                             class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <form action="./edit-user?id=<%= u.getId()%>" method="POST" id="form-update">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Update User Account</span>
                            </div>

                            <div class="form-group mb-4">
                                <label class="form-label" for="firstname">First Name</label>
                                <input id="firstname" class="form-control form-control-lg" placeholder="Enter First Name"
                                       name="firstname" value="<%= u.getFirstName()%>" rules="required" />
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <div class="form-group mb-4">
                                <label class="form-label" for="lastname">Last Name</label>
                                <input id="lastname" class="form-control form-control-lg" placeholder="Enter Last Name"
                                       name="lastname" value="<%= u.getLastName()%>" rules="required"/>
                                <span class="form-message" style="color: red"></span>
                            </div>


                            <div class="form-group mb-4">
                                <label class="form-label" for="status">Status</label>
                                <select id="status" class="form-select" name="status" rules="required">
                                    <option value="1" <%= u.getStatus() == 1 ? "selected = \"true\"" : ""%>>Active</option>
                                    <option value="2" <%= u.getStatus() == 2 ? "selected = \"true\"" : ""%>>In Active</option>
                                </select>
                                <span class="form-message" style="color: red"></span>
                            </div>


                            <div class="form-group mb-4">
                                <label class="form-label" for="role">Role</label>
                                <select id="role" class="form-select" name="role" rules="required">
                                    <option value="1" <%= u.getRole() == 1 ? "selected = \"true\"" : ""%>>Guest</option>
                                    <option value="2" <%= u.getRole() == 2 ? "selected = \"true\"" : ""%>>Admin</option>
                                </select>
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Update</button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't want to update? <a href="user"
                                                                                                 class="link-danger">Go back</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <br>
            <br>
            <br>
            <br>
            <%@include file="footer.jsp" %>
        </section>
        <%@include file="./JS.jsp" %>
        <script>
            Validator("#form-update");
        </script>
    </body>

</html>
