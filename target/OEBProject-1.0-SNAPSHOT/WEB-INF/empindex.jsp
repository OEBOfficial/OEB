<%-- 
    Document   : empindex
    Created on : Mar 17, 2017, 10:25:35 PM
    Author     : USER
--%>
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
        <title>จัดการพนักงาน</title>
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
                        <div class="clearfix"></div>                   
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h4>จัดการข้อมูลพนักงาน</h4>
                                    </div>     
                                    <a href="javascript:void(0)" data-toggle="modal" data-target="#addEmp" class="plusemp">
                                        <i class="fa fa-plus-circle"></i> เพิ่มพนักงาน
                                    </a>
                                    <!--                                    <div class="x_title">
                                                                            <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#addEmp">เพิ่มพนักงาน</button>
                                                                            <div class="clearfix"></div>
                                                                        </div>-->
                                    <div class="x_content">
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th class="table-rows"">ชื่อ</th>
                                                    <th class="table-rows"ตำแหน่ง</th>
                                                <th class="table-rows">ประเภท</th>
                                                <th class="table-rows">ค่าจ้างต่อหน่วยเวลา</th>
                                                <th class="table-rows">ตัวเลือก</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${employees}" var="e" varStatus="vs">
                                                <tr id="tr${e.empNo}">
                                                    <td>${e.empName}</td>
                                                    <td>${e.positionName}</td>
                                                    <td>${e.empTypeName}</td>
                                                    <td>${e.specPay!=null?e.specPay:'จ่ายตามตำแหน่ง'}</td>
                                                    <td>
                                                        <a onclick="editEmp(${e.empNo})" href="javascript:void(0)" data-toggle="modal" data-target="#editEmpBtn"><i class="fa fa-edit"></i> </a> 
                                                        <a onclick="delEmp(${e.empNo}, '${e.empName}')" href="javascript:void(0)"><i class="fa fa-trash"></i></a>
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
                        <div class="modal fade" id="addEmp" role="dialog">
                            <div class="modal-dialog">
                                <!-- เนือหาของ Modal ทั้งหมด -->
                                <div class="modal-content">
                                    <!-- ส่วนหัวของ Modal -->
                                    <div class="modal-header">
                                        <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">เพิ่มพนักงานใหม่</h4>
                                    </div>
                                    <!-- ส่วนเนื้อหาของ Modal -->
                                    <div class="modal-body">
                                        <form class="form-horizontal form-label-left input_mask" action="AddEmpServlet" method="post">
                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                <input type="text" class="form-control" name="empName" placeholder="ชื่อ - นามสกุล" required>
                                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <div class="form-group">    
                                                        <div id="gender" class="btn-group" data-toggle="buttons">
                                                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="gender" value="M" required> &nbsp; ชาย &nbsp;
                                                            </label>
                                                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="gender" value="F" required> &nbsp; หญิง &nbsp;
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <input type="text" class="form-control" name="telNo" placeholder="เบอร์โทรศัพท์มือถือ" required>
                                                    <span class="fa fa-phone form-control-feedback right" aria-hidden="true"></span>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" >
                                                    <select name="empPos" class="form-control" required>
                                                        <option value="0">---- เลือกตำแหน่ง ----</option>
                                                        <c:forEach items="${empPos}" var="ep">
                                                            <option value="${ep.positionNo}">${ep.positionName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>


                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <label class="btn btn-default" id="1" value="1" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="etype" required> Full - Time
                                                        </label>
                                                        <label class="btn btn-default"  id="2" value="2" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="etype" required> Part - Time
                                                        </label>
                                                        <label class="btn btn-default"  id="3" value="3" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="etype" required> Training
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" >
                                                    <select id="empType" name="empType" class="form-control" disabled required>
                                                        <option value="0">---- เลือกประเภทการจ่าย ----</option>
                                                        <option value="1">รายชั่วโมง</option>
                                                        <option value="2">รายวัน</option>
                                                        <option value="3">รายเดือน</option>
                                                    </select>
                                                </div>


                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <label class="btn btn-default" id="addgeneral" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="payType" required> จ่ายตามตำแหน่ง
                                                        </label>
                                                        <label class="btn btn-default" id="addspecial" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="payType" required> จ่ายรายคน
                                                        </label>
                                                    </div>
                                                </div>


                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <input type="text" class="form-control" id="addspecpay" placeholder="ค่าจ้าง" name="specPay" disabled required>
                                                    <span class="fa fa-usd form-control-feedback right" aria-hidden="true"></span>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <!-- ปุ่มกดปิด (Close) ตรงส่วนล่างของ Modal -->
                                                <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                                    <button type="submit" class="btn btn-success">ตกลง</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Modal Content (ADD EMP)-->
                        <!-- Modal Content (EDIT EMP)-->
                        <div class="modal fade" id="editEmpBtn" role="dialog">
                            <div class="modal-dialog">
                                <!-- เนือหาของ Modal ทั้งหมด -->
                                <div class="modal-content">
                                    <!-- ส่วนหัวของ Modal -->
                                    <div class="modal-header">
                                        <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">แก้ไขข้อมูลพนักงาน</h4>
                                    </div>
                                    <!-- ส่วนเนื้อหาของ Modal -->
                                    <div class="modal-body">
                                        <form class="form-horizontal form-label-left input_mask" action="EditEmpServlet" method="post">
                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                <input type="text" class="form-control" name="empName" id="name" placeholder="ชื่อ - นามสกุล" required>
                                                <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <div class="form-group">    
                                                        <div id="gender" class="btn-group" data-toggle="buttons">
                                                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="gender" id="gender-m" value="M" required> &nbsp; ชาย &nbsp;
                                                            </label>
                                                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="gender" id="gender-f" value="F" required> &nbsp; หญิง &nbsp;
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <input type="text" class="form-control" id="telno" name="telNo" placeholder="เบอร์โทรศัพท์มือถือ" required>
                                                    <span class="fa fa-phone form-control-feedback right" aria-hidden="true"></span>
                                                </div>
                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" >
                                                    <select name="empPos" class="form-control" id="empPos" required>
                                                        <c:forEach items="${empPos}" var="ep">
                                                            <option value="${ep.positionNo}">${ep.positionName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>


                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <label class="btn btn-default" id="e1" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="etype" required> Full - Time
                                                        </label>
                                                        <label class="btn btn-default"  id="e2" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="etype" required> Part - Time
                                                        </label>
                                                        <label class="btn btn-default"  id="e3" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="etype" required> Training
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" >
                                                    <select id="eempType" name="empType" class="form-control" disabled required>
                                                        <option value="hourly">รายชั่วโมง</option>
                                                        <option value="daily">รายวัน</option>
                                                        <option value="monthly">รายเดือน</option>
                                                    </select>
                                                </div>


                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="c-specPay" value="1" id="general" required> จ่ายตามตำแหน่ง
                                                        </label>
                                                        <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                            <input type="radio" name="c-specPay" value="2" id="special" required> จ่ายรายคน
                                                        </label>
                                                    </div>
                                                </div>


                                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                    <input type="text" class="form-control" id="specpay" placeholder="ค่าจ้าง" name="specPay" disabled required>
                                                    <span class="fa fa-usd form-control-feedback right" aria-hidden="true"></span>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <!-- ปุ่มกดปิด (Close) ตรงส่วนล่างของ Modal -->
                                                <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                                    <button type="submit" id="editSubmit" name="empNo" class="btn btn-success">ตกลง</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                                                </div>
                                            </div>
                                        </form>

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
    <script src="../handmade/empindex.js"></script>
</body>
</html>