<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dummy Redirect Page</title>
    </head>
    <body>
        <%
            response.sendRedirect("ToEmpServlet");
        %>
    </body>
</html>
