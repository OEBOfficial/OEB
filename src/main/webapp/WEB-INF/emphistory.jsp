<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page import="model.EmployeePosition" %>
<%@page import="model.Employee" %>
<%@page import="model.EmploymentType" %>
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
        <title>ประวัติการทำงาน</title>
        <!-- Bootstrap -->
        <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!-- Datatables -->
        <link href="vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
        <!-- bootstrap-daterangepicker -->
        <link href="vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
        <!-- Custom Theme Style -->
        <link href="build/css/custom.min.css" rel="stylesheet">
        <link rel="stylesheet" href="build/css/OEB_style.css">
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <!-- page content -->
                <div class="container">
                    <jsp:include page="/WEB-INF/include/navbar.jsp" />
                    <div class="right_col" role="main">
                        <div class="">
                            <div class="page-title">
                                <div class="title_right">
                                    <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h4>ประวัติการทำงาน</h4>
                                            <!-- <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#addEmp">เพิ่มพนักงาน</button> -->
                                        </div>
                                        <div class="x_content">
                                            <form class="form-horizontal" action="FilterWorkByDateServlet" method="POST">
                                                <fieldset >
                                                    <div class="control-group show-grid">
                                                        <p id="text-before-calendar">เลือกวันที่ต้องการแสดง</p>
                                                        <div class="controls">
                                                            <div class="input-prepend input-group col-md-6 col-md-offset-4">
                                                                <span class="add-on input-group-addon">
                                                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                                                </span>
                                                                <input type="text" name="date" id="reservation" class="form-control datepicker" value="${daterange}" />
                                                                <button type="submit" class="btn btn-default">
                                                                    <span class="glyphicon glyphicon-search"></span> ค้นหา
                                                                </button>
                                                            </div>   
                                                        </div>
                                                    </div>
                                                </fieldset>                                              
                                            </form>
                                        </div>
                                        <!--                                            <div class="col-md-3">
                                                                                        <input type="checkbox" name="clockin"> เวลาเข้างาน 
                                                                                        <input type="checkbox" name="clockout"> เวลาออกงาน
                                                                                    </div>-->
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th class="table-rows" style="width:16.67%;">วัน/เดือน/ปี</th>
                                                    <th class="table-rows" style="width:16.67%;">ชื่อ</th>
                                                    <th class="table-rows" style="width:16.67%;">เวลาเข้างาน</th>
                                                    <th class="table-rows" style="width:16.67%;">เวลาออกงาน</th>
                                                    <th class="table-rows" style="width:16.67%;">ชั่วโมงงาน</th>
                                                    <th class="table-rows" style="width:16.67%;">ค่าจ้างวันนี้</th>

                                                </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach items="${workhist}" var="wh">
                                                    <tr>
                                                        <td style="text-align:center;">
                                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${wh.fromDate}" />
                                                        </td>
                                                        <td style="text-align:center;">
                                                            ${wh.empName}
                                                        </td>
                                                        <td>
                                                            <fmt:formatNumber type="number" pattern="00.00" value="${Math.floor(wh.fromTime/60)+(wh.fromTime%60)/100}" /> น.
                                                        </td style="text-align:center;">
                                                        <td>
                                                            <fmt:formatNumber type="number" pattern="00.00" value="${Math.floor(wh.toTime/60)+(wh.toTime%60)/100}" /> น.
                                                        </td>
                                                        <td style="text-align:center;">
                                                            <fmt:formatNumber type="number" pattern="#0" value="${Math.floor(((wh.toTime - wh.fromTime)/60))}"/> ชั่วโมง
                                                            <fmt:formatNumber type="number" pattern="#0" value="${Math.floor(((wh.toTime - wh.fromTime)%60))}"/> นาที
                                                        </td>
                                                        <td style="text-align:center;">
                                                            <fmt:formatNumber type="number" pattern="##,###0.00" value="${wh.workingPay}"/> ฿
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
    </div>
    <!-- jQuery -->
    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="vendors/moment/min/moment.min.js"></script>
    <script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="vendors/iCheck/icheck.min.js"></script>
    <!-- Datatables -->
    <script src="vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="vendors/jszip/dist/jszip.min.js"></script>
    <script src="vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="vendors/pdfmake/build/vfs_fonts.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#datatable').DataTable().order([0, 'desc'], [3, 'desc']).draw();
        });

        $(document).ready(function () {
            $("#emptab").parent().addClass('active');
            $("#emptab").parent().find('ul').css('display', 'block');
            $("#workhist").parent().addClass('active');
        });
    </script>
</body>
</html>