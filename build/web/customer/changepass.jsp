<%-- 
    Document   : account
    Created on : Mar 14, 2023, 9:13:52 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
        <div class="form-repass">
            <div class="form-content">
                <div class="form-sub">
                    <form action="change" method="post">
                        <label>Old Pass :<input type="text" name="oldp" required/><br/></label>
                        <label>New Pass :<input type="text" name="newp" pattern=".{8,20}" required=""/><br/></label>
                        <button type="submit">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
