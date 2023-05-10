<%-- 
    Document   : account
    Created on : Mar 14, 2023, 9:13:52 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>

    </style>
    <body>
        <jsp:include page="headerc.jsp" />
        <div class="main-acc">
            <div class="mid">
                <span>
                    <a href="change">Change Password</a>
                </span>
                <span>
                    <a href="bought">Products purchased</a>
                </span>
            </div>
        </div>

        <div class="main-cart">
            <div class="classifi">
                <c:forEach items="${requestScope.listCart}" var="list">
                    <div class="sub-cart">
                        <c:if test="${list.key!=0}">
                            <p>
                                Code orders: ${list.key}
                            </p>
                        </c:if>
                        <c:forEach items="${list.value}" var="p">
                            <div class="containersc">
                                <div class="img-ic">
                                    <img src="${p.img}" alt="alt"/>
                                </div>
                                <div class="detail">
                                    <div class="pro-name">
                                        ${p.productName}
                                    </div>
                                    <div class="pro-price">
                                        ${p.price} VND
                                    </div>
                                    <div class="product-qlt" style="margin-bottom: 0">
                                        <label>Quantity:</label>
                                        <div class="product-qlt-detail">
                                            <div class="product-qlt-input-cart">
                                                <input type="text"  value="${p.quantity}" readonly=""/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
