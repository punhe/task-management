
<!--Page Directive: xác định ngôn ngữ của trang (Java), kiểu nội dung (text/html), và bảng mã trang (UTF-8).-->
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!--Chỉ thị này nhập thư viện thẻ core của JSTL (JavaServer Pages Standard Tag Library), cho phép sử dụng các thẻ JSTL trong trang JSP.-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <%@include file="./header.jsp" %>
        <title>Login</title>
    </head>

    <body>

        <!--Thẻ JSTL này đặt một biến có tên là "cookie" với giá trị là các cookie từ yêu cầu hiện tại.-->
        <c:set var="cookie" value="${pageContext.request.cookies}"/>

        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                             class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">

                        <!--Link tới Servlet doGet LoginController sau đó qua form login.jsp rồi tiến hành về lại doPost của Servlet-->
                        <form action="./login" method="post" id="form-login">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Login with your account</span>
                            </div>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: blue; text-align: left;">${msg1}</h6>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: blue; text-align: left;">${msg2}</h6>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg}</h6>

                            <!-- Nhập username -->
                            <div class="form-group mb-4">
                                <label class="form-label" for="form3Example3">Email</label>
                                <input id="username" class="form-control form-control-lg"
                                       placeholder="Enter email" name="username" rules="required|email"  value="${cookie.u.value}"/>
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <!-- Nhập password -->
                            <div class="form-group mb-3">
                                <label class="form-label" for="form3Example4">Password</label>
                                <input type="password" id="password" class="form-control form-control-lg"
                                       placeholder="Enter password" name="password" rules="required" value="${cookie.p.value}" />
                                <span class="form-message" style="color: red"></span>
                            </div>

                            <div class="d-flex justify-content-between align-items-center">
                                <!-- Checkbox nhớ mật khẩu -->
                                <div class="form-check mb-0">
                                    <input class="form-check-input me-2" type="checkbox" ${cookie.r.value != null ? 'checked' : ''} name="remember" value="ON" id="form2Example3" />
                                    <label class="form-check-label" for="form2Example3">
                                        Remember me
                                    </label>
                                </div>    
                                <!--Link tới jsp quên mật khẩu-->
                                <a href="./forget" class="text-body">Forgot password?</a>
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>

                                <!--Link tới JSP đăng ký hoặc trở về trang home-->
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="register.jsp"
                                                                                                  class="link-danger">Register</a></p>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Home page?<a href="index.jsp"
                                                                                     class="link-danger"> Go back</a></p>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <%@include file="./footer.jsp" %>
        </section>
        <%@include file="./JS.jsp" %>
        <script>
//            Check biểu mẫu login
            Validator("#form-login");
        </script>
    </body>

</html>