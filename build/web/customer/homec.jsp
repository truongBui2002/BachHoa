<%-- 
    Document   : home
    Created on : Mar 5, 2023, 5:48:16 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <script>
            function clickk(productId) {
                window.location = "detail?productId=" + productId;
            }
            function submitCls() {
                const forms = document.getElementsByTagName('form');
                for (let i = 0; i < forms.length; i++) {
                    console.log(i);
                    forms[i].submit();
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="headerc.jsp"/>
        <div class="main">
            <div class="page-content">
                <div class="container-content">
                    <div class="row">
                        <div class="row-left">
                            <div class="row-left-p1">
                                <span class="row-t">
                                    Filter
                                </span>
                                <form id="clsDele" action="homec" method="post">
                                    <button id="delete" onclick="submitCls()" name="delete" value="true">
                                        Delete all
                                    </button>
                                </form>
                            </div>
                            <div class="row-left-p2">
                                <span class="row-t">
                                    Price
                                </span>
                                <form id="clsPri" action="homec" method="post">
                                    <span class="row-l">
                                        <label><input type="radio" name="price" value="1" ${requestScope.price == 1?'checked':''} onclick="submitCls()"/> 0đ-50.000đ</label>
                                        <label><input type="radio" name="price" value="2" ${requestScope.price == 2?'checked':''} onclick="submitCls()" /> 50.000đ-100.000đ</label>
                                        <label><input type="radio" name="price" value="3" ${requestScope.price == 3?'checked':''} onclick="submitCls()" /> 100.000-200.000đ</label>
                                        <label><input type="radio" name="price" value="4" ${requestScope.price == 4?'checked':''} onclick="submitCls()" /> 200.000-400.000</label>
                                    </span>
                            </div>
                            <div class="row-left-p3">
                                <span class="row-t">
                                    Classification
                                </span>
                                <span class="row-l">
                                    <c:forEach items="${requestScope.listb}" var="b">
                                        <label>
                                            <input type="checkbox" name="brand" value="${b.brand}"
                                                   onclick="submitCls()" 
                                                   ${requestScope.listSelecteds.contains(b.brand)?'checked':''}
                                                   /> ${b.brand}
                                        </label>
                                    </c:forEach> 
                                    </form>
                                </span>
                            </div>
                        </div>
                        <div class="row-right">
                            <div class="product">
                                <c:forEach items="${requestScope.listp}" var="p">
                                    <div class="block" onclick="clickk(${p.productId})">
                                        <div class="block-img"><img src="${p.img}" alt=""></div>
                                        <div class="block-name">${p.productName}</div>
                                        <div class="block-price">${p.price}</div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
