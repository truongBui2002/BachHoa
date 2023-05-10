<%-- 
    Document   : headera
    Created on : Mar 12, 2023, 11:58:54 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/stylehc.css"/>
        <link rel="stylesheet" href="css/stylecd.css"/>
        <link rel="stylesheet" href="css/stylec.css"/>
        <link rel="stylesheet" href="css/styleacc.css"/>
    </head>
    <body>
        <header>
            <div class="container">
                <!-- Logo, trang chủ, tìm kiếm -->
                <div class="header-left">
                    <div class="logo"><span class="logo-in"><img src="img/logo.png" alt="logo">TruongBui</span></div>
                    <div class="main-nav">
                        <ul class="menu">
                            <li><a href="homec">Home</a></li>
                            <li>
                                <form action="searchc">
                                    <div class="search">
                                        <input name="nameP" id="sear" type="search" placeholder="search by name...."/>
                                        <button type="submit"><img src="img/search.png" alt="search" /></button>
                                    </div>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- Giỏ hàng, tài khoản -->
                <div class="header-right">
                    <div class="info">
                        <div class="sub-info1">
                            <a href="cart">
                                <img src="img/cart.png" alt="">
                            </a>
                        </div>
                        <div class="sub-info2">
                            <c:if test="${sessionScope.account!=null}">
                                <a href="account">
                                    <label>
                                        <img src="img/user.png" alt="">
                                        <span>${sessionScope.account.userName}</span>
                                    </label>
                                </a>
                            </c:if>
                            <c:if test="${sessionScope.account==null}">
                                <a href="login">
                                    <label>
                                        <img src="img/user.png" alt="">
                                        <span>Login</span>
                                    </label>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
