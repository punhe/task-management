<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.se4f7.prj301.constants.Constants" %>
<%@page import="com.se4f7.prj301.entities.ToDoEntity"%>
<%@page import="com.se4f7.prj301.entities.CommentEntity"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="com.se4f7.prj301.service.AuthService"%>
<%@page import="com.se4f7.prj301.service.impl.AuthServiceImpl"%>
<%@page import="com.se4f7.prj301.dto.response.UserResponseDto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Comments</title>
        <%@include file="header.jsp" %>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>    
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

            List<CommentEntity> comments = (List<CommentEntity>) request.getAttribute("comment");
        %>

        <div class="container card-container">
            <%@include file="header.jsp" %>
            <div class="card p-4">
                <div class="fieldset-container">
                    <fieldset>
                        <br>
                        <legend>View Message</legend>
                        <form>
                            <br>
                            <div id='view2'>
                                <%
                                    if (comments != null && !comments.isEmpty()) {
                                        for (CommentEntity ce : comments) {
                                %>
                                <div style="display: flex">
                                    <p><%=ce.getText()%> &nbsp; &nbsp; | Time: <%=ce.getCreatedDate()%>, comment by ADMIN</p>
                                </div>
                                <%
                                    }
                                } else {
                                %>
                                <p>No comments available.</p>
                                <%
                                    }
                                %>
                            </div>
                        </form>
                    </fieldset>
                </div>
                <a href="./load-data-user" class="btn btn-primary">Back home</a>
            </div>

        </div>
        <footer class="fixed-bottom bg-light py-2">
            <div class="container">
                <div class="d-flex justify-content-between">
                    <!-- Copyright -->
                    <div class="text-dark mb-3 mb-md-0">
                        &copy; <%= Constants.COPYRIGHT_TEXT%>
                    </div>
                    <!-- Right -->
                    <div>
                        <a href="#!" class="text-dark me-4">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a href="#!" class="text-dark me-4">
                            <i class="fab fa-google"></i>
                        </a>
                        <a href="#!" class="text-dark">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                    </div>
                </div>
            </div>
        </footer>
        <%@include file="JS.jsp" %>
    </body>
</html>
