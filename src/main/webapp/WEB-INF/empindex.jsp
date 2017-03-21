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
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="empindex.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
                        </div>
                        <div class="clearfix"></div>
                        <!-- menu profile quick info -->
                        <div class="profile clearfix">
                            <div class="profile_pic">
                                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                            </div>
                            <div class="profile_info">
                                <span>Welcome,</span>
                                <h2>John Doe</h2>
                            </div>
                        </div>
                        <!-- /menu profile quick info -->
                        <br />
                        <!-- sidebar menu -->
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <ul class="nav side-menu">
                                    <li>
                                        <a id="emptab"><i class="fa fa-user"></i> จัดการพนักงาน <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a id="empdata" href="javascript:void(0)">ข้อมูลพนักงาน</a></li>
                                            <li><a href="index4.html">ตำแหน่งพนักงาน</a></li>
                                            <li><a href="index2.html">ประวัติการทำงาน</a></li>
                                            <li><a href="index3.html">จ่ายเงินพนักงาน</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a><i class="fa fa-edit"></i> E - Menu <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="media_gallery.html">เพิ่ม และ แก้ไข เมนู</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a><i class="fa fa-spoon"></i> Table Layout <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="general_elements.html">จัดการ Layout ของร้าน</a></li>
                                            <li><a href="widgets.html">Widgets</a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a><i class="fa fa-tag"></i> E - Promotion <span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="tables.html">Tables</a></li>
                                            <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- /sidebar menu -->
                        <!-- /menu footer buttons -->
                        <div class="sidebar-footer hidden-small">
                            <a data-toggle="tooltip" data-placement="top" title="Settings">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                            </a>
                        </div>
                        <!-- /menu footer buttons -->
                    </div>
                </div>

                <!-- top navigation -->
                <div class="top_nav">
                    <div class="nav_menu">
                        <nav>
                            <div class="nav toggle">
                                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                            </div>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="">
                                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        <img src="images/img.jpg" alt="">John Doe
                                        <span class=" fa fa-angle-down"></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                                        <li><a href="javascript:;"> Profile</a></li>
                                        <li>
                                            <a href="javascript:;">
                                                <span class="badge bg-red pull-right">50%</span>
                                                <span>Settings</span>
                                            </a>
                                        </li>
                                        <li><a href="javascript:;">Help</a></li>
                                        <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                                    </ul>
                                </li>
                                <li role="presentation" class="dropdown">
                                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                        <i class="fa fa-envelope-o"></i>
                                        <span class="badge bg-green">6</span>
                                    </a>
                                    <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <div class="text-center">
                                                <a>
                                                    <strong>See All Alerts</strong>
                                                    <i class="fa fa-angle-right"></i>
                                                </a>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!-- /top navigation -->
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
                                        <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#addEmp">เพิ่มพนักงาน</button>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>ชื่อ</th>
                                                    <th>ตำแหน่ง</th>
                                                    <th>ประเภท</th>
                                                    <th>ค่าจ้าง</th>
                                                    <th>ตัวเลือก</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${employees}" var="e" varStatus="vs">
                                                    <tr id="tr${e.empNo}">
                                                        <td>${e.empName}</td>
                                                        <td>${e.positionName}</td>
                                                        <td>${e.empTypeName}</td>
                                                        <td>${e.specPay}</td>
                                                        <td>
                                                            <button type="button" onclick="editEmp(${e.empNo})" class="btn btn-success" data-toggle="modal" data-target="#editEmpBtn">แก้ไขข้อมูล</button>
                                                            <button type="submit" onclick="delEmp(${e.empNo})" class="btn btn-danger" data-toggle="modal">ลบข้อมูล</button>
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
                                                            <c:forEach items="${empPos}" var="ep">
                                                                <option value="${ep.positionNo}">${ep.positionName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>


                                                    <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                        <div class="btn-group" data-toggle="buttons">
                                                            <label class="btn btn-default" id="1" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="etype" required> Full - Time
                                                            </label>
                                                            <label class="btn btn-default"  id="2" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="etype" required> Part - Time
                                                            </label>
                                                            <label class="btn btn-default"  id="3" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="etype" required> Training
                                                            </label>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" >
                                                        <select id="empType" name="empType" class="form-control" disabled required>
                                                            <option value="hourly">รายชั่วโมง</option>
                                                            <option value="daily">รายวัน</option>
                                                            <option value="monthly">รายเดือน</option>
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
                                                                <input type="radio" name="payType" id="general" required> จ่ายตามตำแหน่ง
                                                            </label>
                                                            <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                                <input type="radio" name="payType" id="special" required> จ่ายรายคน
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
                                                        <button type="submit" class="btn btn-success">ตกลง</button>
                                                        <button type="button" id="editSubmit" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /Modal Content (EDIT EMP)-->
                            <!-- footer content -->
                            <!--                                <footer>
                                                                <div class="pull-right">
                                                                    Order Eat Bill by SIT KMUTT.
                                                                </div>
                                                                <div class="clearfix"></div>
                                                            </footer>-->
                            <!-- /footer content -->
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

    <script>
                                                                $(document).ready(function () {
                                                                    $("#emptab").click();
                                                                    $("#empdata").click();
                                                                });
                                                                $("#1").click(function () {
                                                                    $("#empType").attr("disabled", false);
                                                                    $("#empType option:nth-child(1)").val('1');
                                                                    $("#empType option:nth-child(2)").val('2');
                                                                    $("#empType option:nth-child(3)").val('3');
                                                                });
                                                                $("#2").click(function () {
                                                                    $("#empType").attr("disabled", false);
                                                                    $("#empType option:nth-child(1)").val('4');
                                                                    $("#empType option:nth-child(2)").val('5');
                                                                    $("#empType option:nth-child(3)").val('6');
                                                                });
                                                                $("#3").click(function () {
                                                                    $("#empType").attr("disabled", false);
                                                                    $("#empType option:nth-child(1)").val('7');
                                                                    $("#empType option:nth-child(2)").val('8');
                                                                    $("#empType option:nth-child(3)").val('9');
                                                                });

                                                                $("#e1").click(function () {
                                                                    $("#eempType").attr("disabled", false);
                                                                    $("#eempType option:nth-child(1)").val('1');
                                                                    $("#eempType option:nth-child(2)").val('2');
                                                                    $("#eempType option:nth-child(3)").val('3');
                                                                });
                                                                $("#e2").click(function () {
                                                                    $("#eempType").attr("disabled", false);
                                                                    $("#eempType option:nth-child(1)").val('4');
                                                                    $("#eempType option:nth-child(2)").val('5');
                                                                    $("#eempType option:nth-child(3)").val('6');
                                                                });
                                                                $("#e3").click(function () {
                                                                    $("#eempType").attr("disabled", false);
                                                                    $("#eempType option:nth-child(1)").val('7');
                                                                    $("#eempType option:nth-child(2)").val('8');
                                                                    $("#eempType option:nth-child(3)").val('9');
                                                                });

                                                                $("#addspecial").click(function () {
                                                                    $("#addspecpay").attr('disabled', false);
                                                                });

                                                                $("#addgeneral").click(function () {
                                                                    $("#addspecpay").val('');
                                                                    $("#addspecpay").attr('disabled', true);
                                                                });

                                                                $("#special").click(function () {
                                                                    $("#specpay").attr('disabled', false);
                                                                });

                                                                $("#general").click(function () {
                                                                    $("#specpay").val('');
                                                                    $("#specpay").attr('disabled', true);
                                                                });

                                                                function editEmp(empNo) {
                                                                    $.ajax({
                                                                        type: "POST",
                                                                        url: "SetEmpAjaxServlet",
                                                                        dataType: "json",
                                                                        data: "empno=" + encodeURIComponent(empNo),
                                                                        success: function (json) {
                                                                            $("#editSubmit").val(json.empNo);
                                                                            $("#name").val(json.empName);
                                                                            if (json.gender == 'M') {
                                                                                $("#gender-m").click();
                                                                            } else {
                                                                                $("#gender-f").click();
                                                                            }
                                                                            $("#telno").val(json.telNo);
                                                                            $("#empType").val(json.empTypeNo);
                                                                            var positionNo = json.positionNo;
                                                                            if (positionNo <= 3) {
                                                                                $("#e1").click();
                                                                                $("#eempPos").val(positionNo);
                                                                            } else if (positionNo <= 6) {
                                                                                $("#e2").click();
                                                                                $("#eempPos").val(3 + positionNo);
                                                                            } else {
                                                                                $("#e3").click();
                                                                                $("#eempPos").val(6 + positionNo);
                                                                            }
                                                                            if (json.specPay == "null") {
                                                                                $("#general").click();
                                                                                $("#specpay").val('');
                                                                            } else {
                                                                                $("#special").click();
                                                                                $("#specpay").val(json.specPay);
                                                                            }
                                                                        }
                                                                    });
                                                                }

                                                                function delEmp(empNo) {
                                                                    swal({
                                                                        title: "คุณมั่นใจว่าจะลบใช่หรือไม่ ?",
                                                                        text: "คุณจะไม่สามารถกู้ข้อมูลพนักงานกลับมาได้อีก!",
                                                                        type: "warning",
                                                                        showCancelButton: true,
                                                                        confirmButtonColor: "#DD6B55",
                                                                        cancelButtonText: "ยกเลิก",
                                                                        confirmButtonText: "ใช่, ฉันต้องการลบ",
                                                                        closeOnConfirm: false
                                                                    },
                                                                            function () {
                                                                                $.ajax({
                                                                                    type: "Post",
                                                                                    url: "DelEmpAjaxServlet",
                                                                                    dataType: "text",
                                                                                    data: "empno=" + encodeURIComponent(empNo),
                                                                                    success: function (result) {
                                                                                        swal("เรียบร้อย", "ข้อมูลพนักงานถูกลบแล้ว", "success");
                                                                                        $("#tr" + empNo).remove();
                                                                                    }
                                                                                });
                                                                            });
                                                                }
    </script>
</body>
</html>