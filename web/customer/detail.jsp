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

        function checkQlt() {
            const myInput = document.getElementById("qlt");
            const inputValue = myInput.value;
            if (!(/^\d+$/.test(inputValue) && parseInt(inputValue) > 0)) {
                myInput.value = 1;
            }
        }
        function add() {
            var input = document.getElementById("qlt");
            var value = parseInt(input.value);
            if (value < 20) {
                input.value = value + 1;
            }
        }

        function sub() {
            var input = document.getElementById("qlt");
            var value = parseInt(input.value);
            if (value > 1) {
                input.value = value - 1;
            }
        }
        function loadData(){
            var input = document.getElementById("qlt");
            var quantity = parseInt(input.value);
             var xhr = new XMLHttpRequest();
             xhr.open("GET", "addItem?productId="+${p.productId}+"&quantity="+quantity);
             xhr.send();
        }
    </script>
    <body>
        <jsp:include page="headerc.jsp"/>
        <c:set var="p" value="${requestScope.p}"/>
        <div class="main">
            <div class="page-contentd">
                <div class="container-contentd">
                    <div class="rowd">
                        <div class="rowd-left">
                            <div class="img-p">
                                <img src="${p.img}" alt="alt"/>
                            </div>
                        </div>
                        <div class="rowd-right">
                            <div class="product-name">
                                ${p.productName}
                            </div>
                            <div class="product-price">
                                ${p.price} VND
                            </div>
                            <div class="product-descr">
                                <p>
                                    ${p.description}
                                </p>
                            </div>
                            <div class="product-qlt">
                                <label>Quantity:</label>
                                <div class="product-qlt-detail">
                                    <div class="product-qlt-input">
                                        <div class="product-qlt-sub">
                                            <button class="button-sub" id="sub" onclick="sub()">
                                                -
                                            </button>
                                        </div>
                                        <input type="number" min="1" max="20" value="1" id="qlt" onchange="checkQlt()">
                                        <div class="product-qlt-add" >
                                            <button class="button-add" id="add" onclick="add()">
                                                +
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="product-cart">
                                <div class="product-cart-block"  onclick="loadData()">
                                    <p>
                                        Add to cart
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
