<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>เช็คเวลาเข้าทำงาน</title>
        <!-- Bootstrap -->
        <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!-- Datatables -->
        <link href="../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
        <!-- Custom Theme Style -->
        <link href="../build/css/custom.min.css" rel="stylesheet">
        <!-- <link href="../vendors/sweetalert/sweetalert.css" rel="stylesheet"> -->        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css">
        <link rel="stylesheet" href="../build/css/OEB_style.css">
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <jsp:include page="/WEB-INF/include/navbar.jsp" />
                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="">
                        <div class="page-title">
                            <div class="title_right">
                                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h4>เช็คเวลาเข้าทำงานวันนี้</h4>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th class="table-rows">ชื่อ</th>
                                                    <th class="table-rows">สถานะเข้างาน</th>
                                                    <th class="table-rows">เวลา</th>                                                    
                                                    <th class="table-rows">ตัวเลือก</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${workhist}" var="wh" varStatus="vs">
                                                    <tr id="tr${wh.empNo}">
                                                        <td>${wh.empName}</td>
                                                        <td>${wh.fromDate != null ? wh.toDate != null ? "ทำงานเสร็จแล้ว" : "กำลังทำงานอยู่" : "ยังไม่เข้างาน"}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${wh.fromDate != null}">
                                                                    <c:choose>
                                                                        <c:when test="${wh.toDate != null}">
                                                                            เข้างาน : <fmt:formatNumber pattern="00.00" value="${Math.floor((wh.fromTime/60))+(wh.fromTime%60)/100}" /> น. ; 
                                                                            ออกงาน : <fmt:formatNumber pattern="00.00" value="${Math.floor((wh.toTime/60))+(wh.toTime%60)/100}" /> น.
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            เข้างาน : <fmt:formatNumber pattern="00.00" value="${Math.floor((wh.fromTime/60))+(wh.fromTime%60)/100}" /> น.
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    -
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${wh.fromDate != null}">
                                                                    <c:choose>
                                                                        <c:when test="${wh.toDate != null}">
                                                                            เสร็จแล้วอะ
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <button type="button" class="btn btn-warning" onclick="clockOut(${wh.workNo},${wh.empNo})">ออกงาน</button>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <button type="button" class="btn btn-success" onclick="clockIn(${wh.empNo})">เข้างาน</button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="../vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="../vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="../vendors/nprogress/nprogress.js"></script>
        <!-- iCheck -->
        <script src="../vendors/iCheck/icheck.min.js"></script>
        <!-- Datatables -->
        <script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
        <script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
        <script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
        <script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
        <script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
        <script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
        <script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
        <script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
        <script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
        <script src="../vendors/jszip/dist/jszip.min.js"></script>
        <script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
        <script src="../vendors/pdfmake/build/vfs_fonts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
        <!-- <script src="../vendors/sweetalert/sweetalert.min.js"></script> -->    
        <!-- Custom Theme Scripts -->
        <script src="../build/js/custom.min.js"></script>
        <script src="../handmade/empcheck.js"></script>
        <script>
            function clockIn(empNo){
                $.ajax({
                    type: "POST",
                    url: "ClockInAjaxServlet",
                    dataType: "text",
                    data: "empNo=" + encodeURIComponent(empNo),
                    success: function (result) {
                        swal("เรียบร้อย", "เช็คชื่อเข้าทำงานเรียบร้อยแล้ว", "success");
                    }
                });
            }
            
            function clockOut(workNo,empNo){
                $.ajax({
                    type: "POST",
                    url: "ClockOutAjaxServlet",
                    dataType: "text",
                    data: {'workNo' : encodeURIComponent(workNo),'empNo' : encodeURIComponent(empNo)},
                    success: function (result) {
//                        swal("เรียบร้อย", "เช็คชื่อเข้าทำงานเรียบร้อยแล้ว", "success");
                    }
                });
            }
        </script>
    </body>
</html>