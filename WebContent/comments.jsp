<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.se4f7.prj301.constants.Constants" %>
<%@page import="com.se4f7.prj301.entities.ToDoEntity"%>
<%@page import="com.se4f7.prj301.entities.CommentEntity"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.Cookie" %>
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

            ToDoEntity toDo = (ToDoEntity) request.getAttribute("comment");
            List<CommentEntity> comments = (List<CommentEntity>) request.getAttribute("comments");
            String msg = (String) request.getAttribute("msg");
        %>

        <div class="container card-container">
            <%@include file="header.jsp" %>
            <div class="card p-4">
                <h2>Comments</h2>
                <div class="comment-container">
                    <form action="./comments?taskId=<%=toDo != null ? toDo.getId() : ""%>" method="POST" class="comment-form">
                        <% if (msg != null) {%>
                        <div class="error-message">
                            <%= msg%>
                        </div>
                        <% }%>
                        <input value="<%=user%>" name="createBy" type="hidden"/>
                        <input value="<%=toDo.getId()%>" name="taskId" type="hidden" />
                        <div>
                            <textarea name="textarea" id="default"></textarea>
                        </div>
                        <br>
                        <div class="comment-btn-wrapper">
                            <input class="btn btn-primary" type="submit" value="Comment" id="submitBtn">
                        </div>
                    </form>
                </div>
                <hr>
                <div class="fieldset-container">
                    <fieldset>
                        <legend>View Message</legend>
                        <form>

                            <div id='view2'>
                                <%
                                    if (comments != null && !comments.isEmpty()) {
                                        for (CommentEntity ce : comments) {
                                %>
                                <div style="display: flex">
                                    <p><%=ce.getText()%>  &nbsp; &nbsp; |  Time: <%=ce.getCreatedDate()%>, comment by ADMIN</p>
                                    &nbsp; &nbsp; &nbsp;
                                    <a href="#" class="delete" onclick="handleDeleteCmt(<%=ce.getId()%>)"><i class="material-icons">&#xE5C9;</i></a>
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
                <a href="./load-data" class="btn btn-primary">Back home</a>
            </div>
        </div>
        <br>
        <br>
        <br>
        <%@include file="footer.jsp" %>
        <%@include file="JS.jsp" %>
        <script>

            function handleDeleteCmt(id) {
                Swal.fire({
                    title: 'Are you sure?',
                    text: "You won't be able to revert this action!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, delete it !',
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire({
                            title: 'Deleted!',
                            text: 'Cmt has been deleted :3',
                            icon: 'success',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: 'OK',
                        }).then((result) => {
                            if (result.isConfirmed) {
                                var taskId = <%=toDo.getId()%>;
                                window.location.href = './delete-cmt?id=' + id + "&taskId=" + taskId;
                            }
                        });
                    }
                    if (!result.isConfirmed) {
                        Swal.fire({
                            title: 'Canceled',
                            text: 'Cmt is safe for now :)',
                            icon: 'error',
                            confirmButtonColor: '#3085d6',
                            confirmButtonText: 'OK',
                        });
                    }
                });
            }
        </script>

    </body>
</html>
