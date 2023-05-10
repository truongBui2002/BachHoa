<%-- 
    Document   : addp
    Created on : Mar 9, 2023, 1:05:06 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/styleadd.css"/>
        <title>Update Product</title>
    </head>
    <body style="background-color: #dbdada">
        <jsp:include page="headerd.jsp"/>
        <div class="d-flex justify-content-center  mt-5">
            <c:set value="${sessionScope.p}" var="p"/>
            <form action="updatep" method="post">
                <div class="formp ">
                    <h6 style="color: red">${requestScope.err}</h6>
                    <div class="formp-sub">
                        <span class="name">ProductId</span>
                        <input class="input" type="text" name="productId" readonly
                               value="${p.productId}"/>
                    </div>
                    <div class="formp-sub">
                        <span class="name">Name</span>
                        <input class="input" type="text" name="productName" 
                               value="${p.productName}"/>
                    </div>
                    <div class="formp-sub">
                        <span class="name">Brand</span>
                        <input class="input" type="text" name="brand"
                               value="${p.brand}"/>
                    </div>
                    <div class="formp-sub formp-sub4">
                        <span class="name">Description</span>
                        <textarea class="des" id="id" name="description">${p.description}</textarea>
                    </div>
                    <div class="formp-sub">
                        <span class="name">Price</span>
                        <input class="input" type="text" name="price" placeholder="positive double"
                               value="${p.price}"/>
                    </div>
                    <div class="formp-sub">
                        <span class="name">Quantity</span>
                        <input class="input" type="text" name="quantity" placeholder="positive integer"
                               value="${p.quantity}"/>
                    </div>
                    <div class="formp-sub">
                        <span class="name">Src img</span>
                        <input class="input" type="text" name="img"
                               value="${p.img}"/>
                    </div>
                    <div class="">
                        <button type="submit" class="button-39">Save</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
