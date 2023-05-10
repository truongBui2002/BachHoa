<%-- 
    Document   : fogetPass
    Created on : Mar 5, 2023, 4:20:25 PM
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
                                        
                                        <form action="register" method="post">
                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Register Account</span>
                                            </div>
                                            <h5 class="fw-normal mb-3 pb-3 ms-3" style="letter-spacing: 1px;">Enter account information</h5>
                                            <c:if test="${requestScope.err != null}">
                                                <h6 style="color: red">${requestScope.err}</h6>
                                            </c:if>
                                            <div class="mb-4">
                                                <input type="text" id="formEx" class="form-control form-control-lg" 
                                                       name="userName" value="${sessionScope.drawUserName}"/>
                                                <label class="form-label" for="formEx">User name</label>
                                            </div>
                                            <div class="mb-4">
                                                <input type="email" id="form2Ex" class="form-control form-control-lg"
                                                       name="email" value="${sessionScope.drawEmail}"/>
                                                <label class="form-label" for="form2Ex">Email address</label>
                                            </div>
                                            <div class="mb-4">
                                                <input type="password" id="form3Ex" class="form-control form-control-lg"
                                                       name="password" pattern=".{8,20}" value="${sessionScope.drawPassword}"/>
                                                <label class="form-label" for="form3Ex">Password (password 8-20 characters)</label>
                                            </div>
                                                       <c:if test="${requestScope.errE!=null}">
                                                           <h6 style="color: red">${requestScope.errE}</h6>
                                                       </c:if>
                                            <div class="pt-1 mb-4">
                                                <input class="btn btn-dark btn-lg btn-block" type="submit" value="Sign up"/>
                                            </div>
                                            <a class="small text-muted" href="forgot">Forgot password?</a>
                                            <p class="pb-lg-2" style="color: #393f81;">Do you have an account ? 
                                                <a href="login" style="color: #393f81;">Login here</a></p>
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
