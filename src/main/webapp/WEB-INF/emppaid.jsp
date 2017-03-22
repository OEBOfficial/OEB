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
        <title>พนักงานทั้งหมด</title>
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
        <link href="../vendors/sweetalert/sweetalert.css" rel="stylesheet">
    </head>
    <body class="nav-md">
        <style>
            .modal-body-test {
                position: relative;
                overflow-y: auto;
                width: 1000px;
                right: 30%
            }
        </style>
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
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th style="width:20%;">ชื่อ</th>
                                                    <th style="width:20%;">ตำแหน่ง</th>
                                                    <th style="width:20%;">ประเภท</th>
                                                    <th style="width:20%;">ค่าจ้างที่คงเหลือเบิกได้</th>
                                                    <th style="width:20%;">ตัวเลือก</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${employees}" var="e" varStatus="vs">
                                                    <tr id="tr${e.empNo}">
                                                        <td style="">${e.empName}</td>
                                                        <td style="">${e.positionName}</td>
                                                        <td style="">${e.empTypeName}</td>
                                                        <td style="">${e.SUMPAY}</td>
                                                        <td>
                                                            <button type="submit" class="btn btn-success" data-toggle="modal"  data-target="#paidDetail" onclick="setemppaid(${e.empNo})" >รายละเอียด</button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- /page content -->
                            <!-- Modal Content (ADD EMP)-->
                            <div class="modal fade" id="paidDetail" role="dialog">
                                <div class="modal-dialog">
                                    <!-- เนือหาของ Modal ทั้งหมด -->
                                    <div class="modal-content modal-body-test">
                                        <!-- ส่วนหัวของ Modal -->
                                        <div class="modal-header">
                                            <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">รายละเอียดเงินเดือนของ ${e.empName}</h4>
                                        </div>
                                        <!-- ส่วนเนื้อหาของ Modal -->
                                        <div class="modal-body">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th style="width:20%">วันที่</th>
                                                        <th style="width:20%">ชื่อ - นามสกุล</th>
                                                        <th style="width:20%">ตำแหน่ง</th>
                                                        <th style="width:20%">รูปแบบพนักงาน</th>
                                                        <th style="width:20%">จำนวนเงินที่ได้รับ</th>
                                                    </tr>
                                                </thead>
                                                <tbody id=workhisttable>
                                                </tbody>
                                            </table>
                                        </div>
                                        <form>
                                            <div class="modal-footer">
                                                <!-- ปุ่มกดปิด (Close) ตรงส่วนล่างของ Modal -->
                                                <div class="col-md-4 col-md-offset-8">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" placeholder="กรุณาใส่จำนวนเงิน..">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button">จ่ายเงิน</button>
                                                        </span>
                                                    </div>
                                                </div>

                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                            <!-- /Modal Content (ADD EMP)-->

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
    <script src="../vendors/sweetalert/sweetalert.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>
    <script src="../handmade/emppaid.js"></script>
</body>
</html>