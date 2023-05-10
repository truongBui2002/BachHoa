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

                                        <form action="resetpass" method="get">
                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h2 fw-bold mb-0">New pass</span>
                                            </div>
                                            <div class="mb-4">
                                                <input type="text" id="form2Ex" class="form-control form-control-lg" 
                                                       name="newP" pattern=".{8,20}"/>
                                                <label class="form-label" for="form2Ex">Enter your new password</label>
                                            </div>
                                            <div class="mb-4">
                                                <input type="text" id="form3Ex" class="form-control form-control-lg" 
                                                       name="reNewP" pattern=".{8,20}"/>
                                                <label class="form-label" for="form3Ex">Re-enter password</label>
                                            </div>
                                            <c:if test="${requestScope.err !=null}">
                                                <h6 style="color: red">${requestScope.err} </h6>
                                            </c:if>
                                            <c:if test="${requestScope.errC !=null}">
                                                <h6 style="color: red">${requestScope.errC} </h6>
                                            </c:if>
                                            <div class="pt-1 mb-4">
                                                <input class="btn btn-dark btn-lg btn-block" type="submit" value="Continue"/>
                                            </div>
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
