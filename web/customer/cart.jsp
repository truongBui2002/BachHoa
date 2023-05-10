<%-- 
    Document   : detail
    Created on : Mar 12, 2023, 9:37:02 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deteil Product</title>
    </head>
    <script>

        function checkQlt(pId) {
            const myInput = document.getElementById(pId);
            const inputValue = myInput.value;
            if (!(/^\d+$/.test(inputValue) && parseInt(inputValue) > 0)) {
                myInput.value = 1;
            }
        }
        function add(pId) {
            var input = document.getElementById(pId);
            var value = parseInt(input.value);
            if (value < 20) {
                input.value = value + 1;
            }
        }

        function sub(pId) {
            var input = document.getElementById(pId);
            var value = parseInt(input.value);
            if (value > 1) {
                input.value = value - 1;
            }
        }
        function updateCart(productId) {
            var input = document.getElementById(productId);
            var quantity = parseInt(input.value);
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "updatecart?productId=" + productId + "&quantity=" + quantity);
            xhr.send();
        }

        function deletep(productId) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "updatecart?delete=" + productId);
            xhr.addEventListener("load", function () {
                if (xhr.status === 200) {
                    location.reload();// tải lại làm mất dữ liệu đã nhập
                }
            });
            xhr.send();
        }
    </script>
    <body>
        <jsp:include page="headerc.jsp"/>
        <c:set var="p" value="${requestScope.p}"/>
        <div class="main">
            <div class="page-contentd">
                <div class="container-contentd" style="border: 0">
                    <div class="rowd">

                        <div class="rowc-left">
                            <c:forEach items="${requestScope.listCart}" var="proCart">
                                <div class="containers">
                                    <div class="img-i">
                                        <img src="${proCart.img}" alt="alt"/>
                                    </div>
                                    <div class="detail">
                                        <div class="pro-name">
                                            ${proCart.productName}
                                        </div>
                                        <div class="pro-price">
                                            ${proCart.price} VND
                                        </div>
                                        <div class="product-qlt" style="margin-bottom: 0">
                                            <label>Quantity:</label>
                                            <div class="product-qlt-detail">
                                                <div class="product-qlt-input">
                                                    <div class="product-qlt-sub">
                                                        <button class="button-sub" id="sub" onclick="sub(${proCart.productId});
                                                                updateCart(${proCart.productId})">
                                                            -
                                                        </button>
                                                    </div>
                                                    <input type="number" min="1" max="20" value="${proCart.quantity}" id="${proCart.productId}"
                                                           onchange="checkQlt(${proCart.productId});
                                                                   updateCart(${proCart.productId})">
                                                    <div class="product-qlt-add" >
                                                        <button class="button-add" id="add" onclick="add(${proCart.productId});
                                                                updateCart(${proCart.productId})">
                                                            +
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="dele" onclick="deletep(${proCart.productId})">
                                        Delete
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="product-cart" style="margin-top: 2rem;">
                                <div class="product-cart-block" onclick="">
                                    <c:set var="qtyP" value="${requestScope.listCart.size()}"/>
                                    <c:if test="${qtyP!=0}">

                                    </c:if>

                                    <button form="info" type="submit">
                                        <p>Buy
                                        </p>
                                    </button>


                                </div>
                            </div>
                        </div>
                        <div class="rowc-right">
                            <c:set var="cu" value="${requestScope.cu}"/>
                            <c:set var="acc" value="${sessionScope.account}" />
                            <div class="info-user">
                                <form id="info" action="cart" method="post">
                                    <c:if test="${requestScope.err!=null}">
                                        <h5>
                                            ${requestScope.err}
                                        </h5>
                                    </c:if>
                                    <input type="text" value="${cu.userName}" placeholder="Name" readonly=""/>
                                    <input type="text" value="${cu.fullName}" placeholder="full name" name="fullName" required/>
                                    <input type="number" value="${cu.phone}" placeholder="Phone Number" required name="phone"/>
                                    <input type="email" value="${acc.email}" placeholder="Email" name="email" readonly=""/>
                                    <select name="shipMode" style="margin-bottom: 1rem">
                                        <option selected>Fast</option>
                                        <option>Standard</option>
                                    </select>
                                    <textarea placeholder="Address" required name="address">${cu.address}</textarea>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
