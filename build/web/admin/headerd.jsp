<%-- 
    Document   : headera
    Created on : Mar 8, 2023, 2:40:39 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styleh.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" type="text/css"/>
        <!-- font roboto -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <title>TruongBui</title>
    </head>
    <body>
        <!--style="position: fixed; top: 0; left: 0; right: 0"-->
        <header> 
            <div class="container" >
                <ul>
                    <!--Cần java ở đây-->
                    <li><img src="img/logo.png" alt="logo" width="40px"/></li>
                    <li><a href="statistics">Statistics</a></li>
                    <li><a href="listpa">Product</a></li>
                    <!--                    <li><a href="order">Customer</a></li>
                                        <li><a href="orderDetails">Order Details</a></li>-->
                </ul>
                <ul>
                    <li>
                        <c:set var="acc" value="${sessionScope.account}"></c:set>
                            <a href="logout">Log out</a>
                            <span id="admin">${acc.userName}</span>
                    </li>
                </ul> 
            </div>
        </header>
    </body>
</html>
