<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Forget Password</title>
        <%@include file="header.jsp" %>
    </head>

    <body>
        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                             class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <form action="./reset-pass" method="post" id="form-resetpass">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Reset password</span>
                            </div>

                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg}</h6>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg1}</h6>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: blue; text-align: left;">${msg2}</h6>

                            <div class="form-group mb-3">
                                <label class="form-label" for="password">New Password</label>
                                <input type="password" id="password" class="form-control form-control-lg pass"
                                       placeholder="Enter password" name="newPassword" rules="required|min:13|upper|lower|number|special"/>
                                <span class="form-message" style="color: red"></span>
                            </div>
                            <div class="form-group mb-3">
                                <label class="form-label" for="password">Confirm Password</label>
                                <input type="password" id="password" class="form-control form-control-lg pass"
                                       placeholder="Enter confirm password" name="confirmPassword" rules="required|min:13|upper|lower|number|special"/>
                                <span class="form-message" style="color: red"></span>
                            </div>
                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Change Password</button>
                            </div>
                        </form>
                    </div>
                </div>
                <%@include file="footer.jsp" %>
        </section>

        <%@include file="JS.jsp" %>
        <script>
            Validator("#form-resetpass");
        </script>
    </body>

</html>
