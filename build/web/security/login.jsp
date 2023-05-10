<%-- 
    Document   : login
    Created on : Mar 5, 2023, 5:14:17 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    </head>
    <body style="background-color: #dbdada">
        <section class="vh-100" >
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-6 col-lg-8 col-md-12">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black"> 
                                        <form action="login" method="post">
                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Sign in</span>
                                            </div>

                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your account</h5>
                                            <c:if test="${requestScope.err!= null}">
                                                <h6 style="color: red">${requestScope.err}</h6>
                                            </c:if>
                                            <div class="mb-4">
                                                <input type="text" id="form2Ex" class="form-control form-control-lg" name="userName"
                                                       value="${cookie.userName.value}"/>
                                                <label class="form-label" for="form2Ex">User name or email address</label>
                                            </div>

                                            <div class="mb-4">
                                                <input type="password" id="form3ExEx" class="form-control form-control-lg" name="password"
                                                       value="${cookie.password.value}"/>
                                                <label class="form-label" for="form3Ex">Password</label>
                                            </div>
                                            <label>
                                                <input type="checkbox" ${(cookie.remember!=null?'checked':'')} name="remember" value="on"> Remember password
                                            </label>
                                            <div class="pt-1 mb-4">
                                                <input class="btn btn-dark btn-lg btn-block" type="submit" value="Login"/>
                                            </div>
                                            <p class="mt-5 pb-lg-2" style="color: #393f81;">
                                                <a class="small text-muted" href="forgot">Forgot password?</a><br/>
                                                Don't have an account? 
                                                <a href="register" style="color: #393f81;">Register here</a>
                                            </p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
