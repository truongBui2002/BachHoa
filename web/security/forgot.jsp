<%-- 
    Document   : fogetPass
    Created on : Mar 5, 2023, 4:20:25 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset password</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/styleB.css"/>
        <style>

        </style>
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
                                        <form action="forgot" method="get">
                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Find Your Account</span>
                                            </div>
                                            <div class="mb-4">
                                                <!--Email lấy lại pass-->
                                                <input style="width:80%" type="email" class="form-control form-control-lg d-inline-block ps-2" 
                                                       placeholder="Email address" name="fEmail" value="${sessionScope.fEmail}" ${(sessionScope.status)?'readonly':''}/>
                                                <button class="button-39" type="submit">Search</button>
                                            </div>
                                        </form>
                                        <c:if test="${requestScope.errEmail != null}">
                                            <h6 style="color: red">${requestScope.errEmail}</h6>
                                        </c:if>
                                        <form action="forgot" method="post">
                                            <c:if test="${sessionScope.status}">
                                                <div class="mb-4">
                                                    <input style="width:40%" type="text" id="form2Ex" class="form-control form-control-lg d-inline-block me-3" 
                                                           name="fCode"/>
                                                    <!--Gửi lại mã xác thực--> 
                                                    <a href="resend">Resend code</a>
                                                    <label class="form-label d-block" for="form2Ex">Enter the code sent to your email</label>
                                                </div>
                                                <c:if test="${requestScope.errCode!=null}">
                                                    <h6 style="color: red">${requestScope.errCode}</h6>
                                                </c:if>
                                                <div class="pt-1 mb-4">
                                                    <input class="btn btn-dark btn-lg btn-block" type="submit" value="Continue"/>
                                                </div>
                                            </c:if>
                                            <p class="pb-lg-2" style="color: #393f81;">Don't have an account? 
                                                <a href="register" style="color: #393f81;">Register here</a></p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/myCode.js" type="text/javascript"></script>
    </body>
</html>
