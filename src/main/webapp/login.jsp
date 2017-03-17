<%-- 
    Document   : login
    Created on : Mar 17, 2017, 7:05:56 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log-in Page</title>
    </head>
    <body>
        <form method="POST" action="LogInServlet">
            Username : <input type="text" name="username" value="${username}">
            Password : <input type="password" name="password">
            Remember me <input type="checkbox" name="rememberme" ${rememberme!=null?'checked':''}>
            <input type="submit">
            ${msg}
        </form>
    </body>
</html>
