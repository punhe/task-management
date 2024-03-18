<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Forget Password</title>
        <%@include file="./header.jsp" %>
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
                        <form action="./forget" method="post" id="form-forget">
                            <div class="d-flex align-items-center mb-3 pb-1">
                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                <span class="h1 fw-bold mb-0">Forget password</span>
                            </div>
                            <h6 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: red; text-align: left;">${msg}</h6>
                            <div class="form-group mb-4">
                                <label class="form-label" for="form3Example3">Email</label>
                                <input id="username" class="form-control form-control-lg"
                                       placeholder="Enter email" name="username" rules="required|email"/>
                                <span class="form-message" style="color: red"></span>
                            </div>
                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg"
                                        style="padding-left: 2.5rem; padding-right: 2.5rem;">Send OTP</button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">You have remembered the password ? <a href="login.jsp"
                                                                                                              class="link-danger">Go back</a></p>

                            </div>
                        </form>
                    </div>
                </div>
                <%@include file="./footer.jsp" %>
        </section>
        <%@include file="./JS.jsp" %>
        <script>
            Validator("#form-forget");
        </script>
    </body>

</html>
