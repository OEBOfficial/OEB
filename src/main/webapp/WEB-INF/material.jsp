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
        <title>ข้อมูลวัตถุดิบ</title>
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
        <!-- Custom Theme Style -->
        <link href="build/css/custom.min.css" rel="stylesheet">
        <!-- <link href="vendors/sweetalert/sweetalert.css" rel="stylesheet"> -->        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.css">
        <link rel="stylesheet" href="build/css/OEB_style.css">
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
                                        <h4>ข้อมูลวัตถุดิบ</h4>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <form action="ManageMaterialServlet" method="POST">
                                            <p>
                                                <a href="javascript:void(0)" data-toggle="modal" data-target="#addMatType"  class="btn btn-success btn-sm"><i class="fa fa-plus-circle"></i>&nbsp; เพิ่มวัตถุดิบ</a>
                                                <button type="submit" name="submit" value="del" href="javascript:void(0)" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i>&nbsp; ลบที่เลือก</button>
                                            </p>
                                            <table id="datatable-checkbox" class="table table-striped table-bordered bulk_action1">
                                                <thead>
                                                    <tr>
                                                        <th style="width:3%;text-align: center;"><input type="checkbox" class="flat" id="check-all-1"></th>
                                                        <th style="text-align:center;">ชื่อวัตถุดิบ</th>
                                                        <th style="text-align:center;">ชื่อประเภท</th>
                                                        <th style="text-align:center;">ตัวเลือก</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${materials}" var="m" varStatus="vs">
                                                        <tr id="tr${m.matNo}">
                                                            <td style="text-align:center;"><input type="checkbox" name="table_records" value="${m.matNo}" class="flat"></td>
                                                            <td style="text-align:center;"><span id="materialNo${m.matNo}">${m.matName}</span></td>
                                                            <td style="text-align:center;"><span id="matType${m.matNo}" >${matTypes[m.matTypeNo].matTypeName}</span></td>
                                                            <td style="text-align:center;">
                                                                <a href="javascript:void(0)" onclick="setMaterial(${m.matNo})" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#editMaterial"><i class="fa fa-pencil"></i>&nbsp; แก้ไข</a>
                                                                <a href="javascript:void(0)" onclick="delMaterial(${m.matNo})" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i>&nbsp; ลบ</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal Content (ADD Mat Type)-->
                    <div class="modal fade" id="addMatType" role="dialog">
                        <div class="modal-dialog">
                            <!-- เนือหาของ Modal ทั้งหมด -->
                            <div class="modal-content">
                                <!-- ส่วนหัวของ Modal -->
                                <div class="modal-header">
                                    <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">เพิ่มประเภทอาหาร</h4>
                                </div>
                                <!-- ส่วนเนื้อหาของ Modal -->
                                <div class="modal-body">
                                    <form class="form-horizontal form-label-left input_mask" action="AddMaterialServlet" method="post">
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                <input type="text" class="form-control" name="matName" placeholder="ชื่อวัตถุดิบ" required>
                                                <span class="fa fa-cutlery form-control-feedback right" aria-hidden="true"></span>
                                            </div>
                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" >
                                                <select name="matTypeNo" class="form-control" required>
                                                    <c:forEach items="${matTypes}" var="mt">
                                                        <option value="${mt.key}">${mt.value.matTypeName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
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
                    <!-- /Modal Content (ADD MatType)-->
                    <!-- Modal Content (Edit MatType)-->
                    <div class="modal fade" id="editMaterial" role="dialog">
                        <div class="modal-dialog">
                            <!-- เนือหาของ Modal ทั้งหมด -->
                            <div class="modal-content">
                                <!-- ส่วนหัวของ Modal -->
                                <div class="modal-header">
                                    <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">แก้ไขวัตถุดิบ : <span class="editMaterialName"></span></h4>
                                </div>
                                <!-- ส่วนเนื้อหาของ Modal -->
                                <div class="modal-body">
                                    <form class="form-horizontal form-label-left input_mask" action="EditMaterialServlet" method="post">
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                                <input type="text" class="form-control editMaterialName" name="matName" placeholder="ชื่อวัตถุดิบ" required>
                                                <span class="fa fa-cutlery form-control-feedback right" aria-hidden="true"></span>
                                            </div>
                                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" >
                                                <select name="matTypeNo" id="editMatTypeNo" class="form-control" required>
                                                    <c:forEach items="${matTypes}" var="mt">
                                                        <option value="${mt.key}">${mt.value.matTypeName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                                <button type="submit" class="btn btn-success">ตกลง</button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                                            </div>
                                        </div>
                                        <input type="hidden" id="editMaterialNo" name="matNo">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /Modal Content (Edit MatType)-->
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
        <!-- <script src="vendors/sweetalert/sweetalert.min.js"></script> -->    
        <!-- Custom Theme Scripts -->
        <script src="build/js/custom.min.js"></script>
        <script src="handmade/material.js"></script>
    </body>
</html>