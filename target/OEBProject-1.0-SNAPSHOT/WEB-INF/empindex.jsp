<%-- 
    Document   : empindex
    Created on : Mar 17, 2017, 10:25:35 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee</title>
    </head>
    <body>
        <table>
            <tr>
                <td>ชื่อพนักงาน</td>
                <td>บัตร ปชช.</td>
                <td>เพศ</td>
                <td>เบอร์โทรศัพท์</td>
                <td>ตำแหน่ง</td>
                <td>ประเภท</td>
                <td>ค่าจ้าง</td>
                <td>แก้ไข/ลบ</td>
            </tr>
            <c:forEach items="${employees}" var="e">
                <tr>
                    <td>${e.empName}</td>
                    <td>${e.idCardNo}</td>
                    <td>${e.gender=='M'?'ชาย':'หญิง'}</td>
                    <td>${e.telNo}</td>
                    <td>${e.positionName}</td>
                    <td>${e.empTypeName}</td>
                    <td>${e.specPay!=0 ? e.specPay:'จ่ายตามเงิน'}</td>
                    <td>
                        <a onclick="editEmp(${e.empNo})" href="javascript:void(0)">แก้ไข</a>
                        <a onclick="delEmp(${e.empNo})" href="javascript:void(0)">ลบ</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p id="showemp"></p>
        <script>
            function editEmp(empNo){
                $.ajax({
                    type: "POST",
                    url: "SetEmpAjaxServlet",
                    dataType:"text",
                    data : "empno="+encodeURIComponent(empNo),
                    success: function(json){
                        $("#showemp").html(json);
                    }
                });
            }
            
            function delEmp(empNo){
                $.ajax({
                    type: "POST",
                    url: "DelEmpAjaxServlet",
                    dataType:"text",
                    data : "empno="+encodeURIComponent(empNo),
                    success: function(result){
                        alert(result);
                    }
                });
            }
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </body>
</html>
