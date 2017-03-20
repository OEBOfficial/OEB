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
                <tr id="tr${e.empNo}">
                    <td>${e.empName}</td>
                    <td>${e.idCardNo}</td>
                    <td>${e.gender=='M'?'ชาย':'หญิง'}</td>
                    <td>${e.telNo}</td>
                    <td>${e.positionName}</td>
                    <td>${e.empTypeName}</td>
                    <td>${e.specPay != null ? e.specPay:'จ่ายตามเงิน'}</td>
                    <td>
                        <a onclick="editEmp(${e.empNo})" href="javascript:void(0)">แก้ไข</a>
                        <a onclick="delEmp(${e.empNo})" href="javascript:void(0)">ลบ</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="border:1px solid black;">
            <form action="EditEmpServlet" method="POST">
                empName : <input type="text" id="name" name="empName" required><br>
                idCardNo : <input type="text" id="idcard" name="idCardNo" required><br>
                ชาย <input type="radio" id="gender-m" value="M" name="gender" required> 
                หญิง <input type="radio" id="gender-f" value="F" name="gender" required><br>
                telNo : <input type="text" id="telno" name="telNo" required><br>
                positionName : 
                <select name="empPos" id="empPos">
                    <c:forEach items="${empPos}" var="ep">
                        <option value="${ep.positionNo}">${ep.positionName}</option>
                    </c:forEach>
                </select>
                <br>
                empTypeName : 
                <select name="empType" id="empType">
                    <c:forEach items="${empTypes}" var="et">
                        <option value="${et.empTypeNo}">${et.empTypeName}</option>
                    </c:forEach>
                </select>
                <br>
                specPay : 
                จ่ายตามตำแหน่ง <input type="radio" id="general" value="1" name="c-specPay" required> 
                จ่ายพิเศษ <input type="radio" id="special" value="2" name="c-specPay" required> 
                <input type="number" name="specPay" id="specpay" disabled><br>
                <button id="editSubmit" name="empNo">submit</button>
            </form>
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script>
            
            $('#special').click(function() {
                $("#specpay").attr('disabled',false);
             });
             
             $("#general").click(function() {
                $("#specpay").val('');
                $("#specpay").attr('disabled',true);
             });
            
            function editEmp(empNo){
                $.ajax({
                    type: "POST",
                    url: "SetEmpAjaxServlet",
                    dataType:"json",
                    data : "empno="+encodeURIComponent(empNo),
                    success: function(json){
                        $("#editSubmit").val(json.empNo);
                        $("#name").val(json.empName);
                        $("#idcard").val(json.idCardNo);
                        if(json.gender == 'M'){
                            $("#gender-m").click();
                        }else{
                            $("#gender-f").click();
                        }
                        $("#telno").val(json.telNo);
                        $("#empType").val(json.empTypeNo);
                        $("#empPos").val(json.positionNo);
                        if(json.specPay == "null"){
                            $("#general").click();
                            $("#specpay").val('');
                        }else{
                            $("#special").click();
                            $("#specpay").val(json.specPay);
                        }
                    }
                });
            }
            
            function delEmp(empNo){
                $.ajax({
                    type: "Post",
                    url: "DelEmpAjaxServlet",
                    dataType:"text",
                    data : "empno="+encodeURIComponent(empNo),
                    success: function(result){
                        $("#tr"+empNo).remove();
                    }
                });
            }
        </script>
    </body>
</html>
