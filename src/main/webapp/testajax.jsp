<%-- 
    Document   : showemp
    Created on : Mar 18, 2017, 3:43:44 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <input type="button" id="1" class="btn" value="GEN EMP NO 1">
        <p id="display"></p>
        <script>
            $(".btn").click(function(){
                $.ajax({
                    type: "POST",
                    url: "SetEmpPosAjaxServlet",
                    dataType:"text",
                    data : "empposno="+encodeURIComponent(1),
                    success: function(result){
                        alert(result);
                    }
                });
            });
        </script>
    </body>
</html>
