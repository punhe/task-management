<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@page import="com.se4f7.prj301.constants.Constants" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Error Page</title>
        <%@include file="header.jsp" %>
    </head>
    <body class="bg-light">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center mt-5">
                    <img style="height: 150px" src="image/error.png" alt="Error Image" class="img-fluid mb-4">
                    <h1 class="display-4">Access Denied</h1>
                    <p class="lead">You do not have permission to access this page.</p>
                    <p>Please contact the administrator for assistance.</p>
                    <a href="index.jsp" class="btn btn-primary mt-3">Go Back To Home</a>
                </div>
            </div>
        </div>
        <div class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-light" style="width: 100%; position: fixed; bottom: 0;">
            <!-- Copyright -->
            <div class="text-dark mb-3 mb-md-0">
                &copy; <%= Constants.COPYRIGHT_TEXT%>
            </div>
            <!-- Copyright -->

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
        <%@include file="JS.jsp" %>
    </body>
</html>
