<%@page import="java.util.*"%>
<%@page import="java.io.*" %>
<%@page import="com.se4f7.prj301.entities.UserEntity"%>
<%@page import="com.se4f7.prj301.constants.Constants" %>
<%@page import="com.se4f7.prj301.service.AuthService"%>
<%@page import="com.se4f7.prj301.service.impl.AuthServiceImpl"%>
<%@page import="com.se4f7.prj301.dto.response.UserResponseDto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
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

        List<UserEntity> list = (List<UserEntity>) request.getAttribute("list");
        int count = (int) request.getAttribute("count");
    %>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="index.jsp" style="color: black"><i class="fa fa-home" aria-hidden="true"></i>&nbsp; Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <div class="navbar-nav ml-auto">
                    <div class="sherah-controls">
                        <div class="sherah-header__zoom">
                            <button id="sherah-header__full" class="btn btn-light">
                                <svg class="sherah-offset__fill" xmlns="http://www.w3.org/2000/svg" width="33.674" height="26" viewBox="0 0 33.674 26">
                                <g id="Full_Screen_Icon" data-name="Full Screen Icon" transform="translate(-732.046 -400.487)">
                                <path id="Path_198" data-name="Path 198" d="M734.468,402.9c0,1.589,0,3.064,0,4.539,0,.947-.452,1.483-1.213,1.477s-1.189-.535-1.192-1.5q-.008-2.7,0-5.406c0-1.093.411-1.513,1.481-1.517q2.741-.011,5.481,0c.937,0,1.476.467,1.459,1.23-.016.734-.537,1.168-1.441,1.173C737.547,402.907,736.05,402.9,734.468,402.9Z" transform="translate(-0.01 -0.003)"/>
                                <path id="Path_199" data-name="Path 199" d="M906.056,402.9c-1.6,0-3.078.005-4.554,0-.94,0-1.477-.464-1.463-1.226.014-.736.534-1.173,1.436-1.177q2.778-.011,5.556,0c.982,0,1.422.442,1.428,1.42q.015,2.816,0,5.632c-.005.844-.456,1.351-1.169,1.369-.743.018-1.225-.506-1.232-1.381C906.048,406.013,906.056,404.493,906.056,402.9Z" transform="translate(-142.747 0)"/>
                                <path id="Path_200" data-name="Path 200" d="M734.458,526.491c1.593,0,3.068,0,4.543,0,.945,0,1.481.455,1.473,1.216s-.539,1.186-1.5,1.188q-2.741.008-5.481,0c-.988,0-1.432-.439-1.438-1.41q-.016-2.815,0-5.631c0-.874.491-1.4,1.234-1.38.712.019,1.16.526,1.166,1.371C734.466,523.367,734.458,524.888,734.458,526.491Z" transform="translate(0 -102.415)"/>
                                <path id="Path_201" data-name="Path 201" d="M906.057,526.44c0-1.5,0-2.974,0-4.445,0-.968.419-1.5,1.171-1.52.781-.02,1.232.531,1.234,1.531q.007,2.7,0,5.406c0,1.067-.429,1.481-1.516,1.485q-2.7.009-5.406,0c-.962,0-1.492-.431-1.5-1.19s.528-1.21,1.474-1.215c1.427-.007,2.853,0,4.28-.007A2.365,2.365,0,0,0,906.057,526.44Z" transform="translate(-142.748 -102.415)"/>
                                </g>
                                </svg>
                            </button>
                        </div>
                    </div>
                    &nbsp;&nbsp;
                    <% if (user != null) {%>
                    <a class="nav-item nav-link" href="profile.jsp" style="text-decoration: none; color: #000">Welcome, <%= user%></a>
                    <a class="nav-item nav-link" href="./logout" style="text-decoration: none; color: #000"><i class="fa fa-fw fa-sign-out-alt text-dark mr-3"></i></a>
                        <% } %>

                </div>
            </div>
        </nav>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-7">
                                <h2>User <b>Management</b></h2>
                            </div>
                            <div class="col-sm-5">
                                <a href="./load-data" class="btn btn-secondary"><i class="material-icons">home</i> <span>Admin</span></a>
                            </div>
                        </div>
                    </div>
                    <div class="table-filter">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="filter-group">
                                    <form action="./search-user" method="post">
                                        <input type="text" name="search" class="form-control" placeholder="Search..." oninput="searchUByName(this)">
                                        <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Email</th>						
                                <th class="status-filter">
                                    <select onchange="handleFilterStatus(this)">
                                        <option value="-1">Status</option>
                                        <option value="0">All...</option>
                                        <option value="1">Actice</option>
                                        <option value="2">Inactive</option>
                                    </select>
                                </th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th class="status-filter">
                                    <select  onchange="handleFilterRole(this)">
                                        <option value="-1">Role</option>
                                        <option value="0">All...</option>
                                        <option value="1">Guest</option>
                                        <option value="2">Admin</option>
                                    </select>
                                </th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody id="UAjax">
                            <%
                                for (UserEntity userItem : list) {
                            %>
                            <tr>
                                <td><%=userItem.getUsername()%></td>

                                <td>
                                    <span id="status<%=userItem.getId()%>"></span>
                                </td>

                                <td >
                                    <%=userItem.getFirstName()%>
                                </td>

                                <td >
                                    <%=userItem.getLastName()%>
                                </td>

                                <td >
                                    <span id="role<%=userItem.getId()%>"></span>
                                </td>


                                <td>
                                    <%
                                        if (userItem.getRole() == 1) {
                                    %>
                                    <a href="./edit-user?id=<%=userItem.getId()%>" class="settings" title="Settings" data-toggle="tooltip">
                                        <i class="material-icons">&#xE8B8;</i>
                                    </a>
                                    <%
                                    } else {
                                    %>
                                    <a href="error.jsp" class="settings" title="Settings" data-toggle="tooltip">
                                        <i class="material-icons text-danger">block</i>
                                    </a>
                                    <%
                                        }
                                    %>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b><%=count < 5 ? count : 5%></b> out of <b><%=count%></b> entries</div>
                        <ul class="pagination">
                            <c:if test="${tag > 1}">
                                <li class="page-item"><a href="./user?page=${tag - 1}" class="page-link">Previous</a></li>
                                </c:if>
                                <c:forEach begin="1" end="${endP}" var="i">
                                <li class="page-item" class="${tag == i ? "active" : ""}">
                                    <a href="./user?page=${i}" style="text-decoration: none;">${i}</a>
                                    &nbsp;
                                </li>
                            </c:forEach>
                            <c:if test="${tag < endP}">
                                <li class="page-item"><a href="./user?page=${tag + 1}" class="page-link">Next</a></li>
                                </c:if>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
        <br/>
        <br/>
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
        <%@include file="./JS.jsp" %>
        <script>
            $(document).ready(function () {
            <% for (UserEntity userS : list) {%>
                var statusTag = $("#status<%= userS.getId()%>");
                var roleTag = $("#role<%= userS.getId()%>");
                var status = <%= userS.getStatus()%>;
                var role = <%= userS.getRole()%>;

                switch (status) {
                    case 1:
                        statusTag.text("Active").addClass('badge bg-success rounded-pill d-inline text-light');
                        break;
                    case 2:
                        statusTag.text("Inactive").addClass('badge bg-danger rounded-pill d-inline text-light');
                        break;
                }

                switch (role) {
                    case 1:
                        roleTag.text("Guest").addClass('badge bg-info rounded-pill d-inline text-light');
                        break;
                    case 2:
                        roleTag.text("Admin").addClass('badge bg-primary rounded-pill d-inline text-light');
                        break;
                }
            <% }%>
            });
        </script>
    </body>
</html>
