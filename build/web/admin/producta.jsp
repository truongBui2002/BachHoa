<%-- 
    Document   : producta
    Created on : Mar 7, 2023, 8:09:10 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Product</title>
        <link rel="stylesheet" href="css/stylep.css"/>
    </head>
     <!--style="background-color: #dbdada"-->
    <body>
        <jsp:include page="headerd.jsp"/>

        <form class="nav-search " action="listpa" method="post">
            <input  class="form-control me-1" name="key" id="search" type="search" placeholder="Search by id, name, brand">
            <button class="btn btn-outline-primary" type="submit">Search</button>
        </form>
        <div class="box">
            <div class="add"><a href="addp">Add Product</a></div>
        </div>
        <div class="content">
            <div class="sub_content">
                <table class="table align-middle bg-white table-striped table-bordered">
                    <tr style="text-align: center">
                        <th>Id</th>
                        <th>Name</th>
                        <th>Brand</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Img</th>
                    </tr>
                    <c:forEach items="${requestScope.list}" var="p">
                        <tr>
                            <td >${p.productId}</td>
                            <td >${p.productName}</td>
                            <td>${p.brand}</td>
                            <td>${p.description}</td>
                            <td>${p.price}</td>
                            <td>${p.quantity}</td>
                            <td><img src="${p.img}" width="50rem" height="50rem" alt="alt"/></td>
                            <td>
                                <a href="updatep?productId=${p.productId}">Update</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
