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
        <title>เมนูอาหารแบบเดี่ยว</title>
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
                                    <h4>เมนูอาหารแบบเดี่ยวของสาขานี้</h4>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <form action="ManageMenuServlet" method="POST">
                                        <p>
                                            <a href="javascript:void(0)" data-toggle="modal" data-target="#addMenu"  class="btn btn-success btn-sm"><i class="fa fa-plus-circle"></i>&nbsp; เพิ่มเมนู</a>
                                            <button type="submit" name="submit" value="del" href="javascript:void(0)" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i>&nbsp; ลบที่เลือก</button>
                                            <button type="submit" name="submit" value="open" href="javascript:void(0)" class="btn btn-info btn-sm"><i class="fa fa-check-circle"></i>&nbsp; เปิดที่เลือก</button>
                                            <button type="submit" name="submit" value="close" href="javascript:void(0)" class="btn btn-info btn-sm"><i class="fa fa-times-circle"></i>&nbsp; ปิดที่เลือก</button>
                                        </p>
                                        <table id="thisbranchmenu" class="table table-striped table-bordered bulk_action1">
                                            <thead>
                                                <tr>
                                                    <th style="width:3%;"><center><input type="checkbox" class="flat" id="check-all-1"></center></th>
                                            <th style="text-align:center;">ชื่ออาหาร</th>
                                            <th style="text-align:center;">ราคา</th>
                                            <th style="text-align:center;">พร้อมขาย</th>
                                            <th style="text-align:center;">ตัวเลือก</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${menus}" var="m">
                                                    <c:if test="${m.isThisBranchMenu}">
                                                        <tr id="trthis${m.menuNo}">
                                                            <td style="text-align:center;">
                                                    <center><input type="checkbox" name="table_records" value="${m.menuNo}" class="flat"></center>
                                                    </td>
                                                    <td style="text-align:center;">${m.menuNameTH} / ${m.menuNameEN}</td>
                                                    <td style="text-align:center;"><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${m.menuPrice}"/> ฿</td>
                                                    <td style="text-align:center;">${m.isAvailable?'<i class="fa fa-check-circle"></i>':'<i class="fa fa-times-circle"></i>'}</td>
                                                    <c:choose>
                                                        <c:when test="${m.branchNo == restowner.branchNo}">
                                                            <td valign="center" style="text-align:center;">
                                                                <a href="javascript:void(0)" onclick="getMenuByMenuSet(${ms.menuSetNo})" class="btn btn-warning btn-sm"><i class="fa fa-pencil"></i>&nbsp;แก้ไข</a>
                                                                <a href="javascript:void(0)" onclick="delMenu(${m.menuNo})" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i>&nbsp;ลบ</a>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td valign="center" style="text-align:center;">
                                                                <a href="javascript:void(0)" onclick="getMenuByMenuSet(${ms.menuSetNo}, 2)" data-toggle="modal" data-target="#showset"  class="btn btn-dark btn-sm">
                                                                    <i class="fa fa-search"></i>&nbsp; รายละเอียด
                                                                </a>
                                                                <a href="javascript:void(0)" onclick="delMenu(${m.menuNo})" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i>&nbsp;ลบ</a>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h4>เมนูอาหารแบบเดี่ยวของสาขาอื่น</h4>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <form action="ManageMenuServlet" method="POST">
                                        <p>
                                            <button type="addtobranch" class="btn btn-success btn-sm" name="submit" value="addtobranch"><i class="fa fa-arrow-up"></i>&nbsp; เพิ่มเข้าสาขา</button>
                                        </p>
                                        <table id="otherbranchmenu" class="table table-striped table-bordered bulk_action2">
                                            <thead>
                                                <tr>
                                                    <th style="text-align:center;width:3%;">
                                                        <input type="checkbox" class="flat" id="check-all-2">
                                                    </th>
                                                    <th style="text-align:center;">ชื่ออาหาร</th>
                                                    <th style="text-align:center;">ราคา</th>
                                                    <th style="text-align:center;">ตัวเลือก</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${menus}" var="m">
                                                    <c:if test="${!m.isThisBranchMenu && m.isOfficialMenu == 1}">
                                                        <tr id="tr${m.menuNo}">
                                                            <td style="text-align:center;"><input type="checkbox" name="table_records" value="${m.menuNo}" class="flat"></td>
                                                            <td style="text-align:center;">${m.menuNameTH} / ${m.menuNameEN}</td>
                                                            <td style="text-align:center;"><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${m.menuPrice}"/> ฿</td>
                                                            <td style="text-align:center;" valign="center">
                                                                <a href="javascript:void(0)" data-toggle="modal" data-target="#editSetOfficial"  class="btn btn-dark btn-sm"><i class="fa fa-search"></i>&nbsp; รายละเอียด</a>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal Content (ADD MENU)-->
                <div class="modal fade" id="addMenu" role="dialog">
                    <div class="modal-dialog">
                        <!-- เนือหาของ Modal ทั้งหมด -->
                        <div class="modal-content modal-body-forMenuSet">
                            <!-- ส่วนหัวของ Modal -->
                            <div class="modal-header">
                                <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">เพิ่มชุดอาหาร</h4>
                            </div>
                            <!-- ส่วนเนื้อหาของ Modal -->
                            <div class="modal-body ">
                                <form class="form-horizontal form-label-left input_mask" id="addemp" action="#" method="post">
                                    <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
                                        <center><img id="blah" src="https://img.clipartfest.com/1c20817e0b1203f771effa178ccc6b66_cloud-upload-2-icon-upload-clipart_512-512.png" style="width: 250px;height: 250px"  alt="your image"  class="img-thumbnail" />
                                            <input type='file' id="imgInp" /></center>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-6 form-group has-feedback">
                                            <input type="text" class="form-control" name="ชื่อชุด" placeholder="ชื่อชุดเมนู">
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-6 form-group has-feedback">
                                            <input type="text" class="form-control" name="ราคา" placeholder="ราคา">
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-6 form-group has-feedback">
                                            <select name="#" class="form-control" id="#" required>
                                                <option value="">---เลือกประเภทอาหาร---</option>
                                                <c:forEach items="#" var="#">
                                                    <option value="${#}">${#}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-6 form-group has-feedback">
                                            <textarea type="text" class="form-control" name="ชื่อชุด" placeholder="คำอธิบาย"></textarea>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-6 form-group has-feedback">
                                            <div class="form-group">    
                                                <div id="gender" class="btn-group" data-toggle="buttons">
                                                    <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                        <input type="radio" name="gender" id="gender-m" value="M" required> &nbsp; เป็น Official เมนู &nbsp;
                                                    </label>
                                                    <label class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                        <input type="radio" name="gender" id="gender-f" value="F" required> &nbsp; ไม่เป็น Official เมนู &nbsp;
                                                    </label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="x_panel">
                                                <div class="x_title">
                                                    <h2>การปรับแต่งเมนู</h2>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="x_content">
                                                    <p><a href="javascript:void(0)" data-toggle="modal" data-target="#addCustMenu"  class="btn btn-success btn-sm"><i class="fa fa-plus-circle"></i>&nbsp; เพิ่มปรับแต่ง</a></p>
                                                    <table id="datatable-fixed-header" class="table table-striped table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>เลือก</th>
                                                                <th>ชื่อการปรับแต่ง</th>
                                                                <th>ระดับ</th>
                                                            </tr>
                                                        </thead>


                                                        <tbody>
                                                            <tr>
                                                                <td class="a-center ">
                                                                    <input type="checkbox" class="flat" name="table_records">
                                                                </td>
                                                                <td>ข้าว</td>
                                                                <td>###</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="a-center ">
                                                                    <input type="checkbox" class="flat" name="table_records">
                                                                </td>
                                                                <td>ความแซ่บ</td>
                                                                <td>###</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12">
                                            <div class="x_panel">
                                                <div class="x_title">
                                                    <h2>วัตถุดิบ</h2>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="x_content">
                                                    <p><a href="javascript:void(0)" data-toggle="modal" data-target="#addMenuMat"  class="btn btn-success btn-sm"><i class="fa fa-plus-circle"></i>&nbsp; เพิ่มวัตถุดิบ</a></p>
                                                    <table id="datatable" class="table table-striped table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>เลือก</th>
                                                                <th>ชื่อวัตถุดิบ</th>
                                                                <th>ปริมาณ</th>
                                                                <th>หน่วย</th>
                                                            </tr>
                                                        </thead>


                                                        <tbody>
                                                            <tr>
                                                                <td class="a-center ">
                                                                    <input type="checkbox" class="flat" name="table_records">
                                                                </td>
                                                                <td>ข้าว</td>
                                                                <td><input type="number" class="form-control" name="ชื่อชุด" placeholder="ปริมาณ"></td>
                                                                <td>
                                                                    <select name="#" class="form-control" id="#" required>
                                                                        <option value="">---เลือกหน่วย---</option>
                                                                        <c:forEach items="#" var="#">
                                                                            <option value="${#}">${#}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="a-center ">
                                                                    <input type="checkbox" class="flat" name="table_records">
                                                                </td>
                                                                <td>ความแซ่บ</td>
                                                                <td><input type="number" class="form-control" name="ชื่อชุด" placeholder="ปริมาณ"></td>
                                                                <td>
                                                                    <select name="#" class="form-control" id="#" required>
                                                                        <option value="">---เลือกหน่วย---</option>
                                                                        <c:forEach items="#" var="#">
                                                                            <option value="${#}">${#}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>

                                            </div>
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
                <!-- /Modal Content (ADD MENU)--> 

                <!-- Modal Content (ADD MATERIAL)-->
                <div class="modal fade" id="addMenuMat" role="dialog">
                    <div class="modal-dialog">
                        <!-- เนือหาของ Modal ทั้งหมด -->
                        <div class="modal-content modal-body">
                            <!-- ส่วนหัวของ Modal -->
                            <div class="modal-header">
                                <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">เพิ่มวัตถุดิบ</h4>
                            </div>
                            <!-- ส่วนเนื้อหาของ Modal -->
                            <div class="modal-body ">
                                <form class="form-horizontal form-label-left input_mask" id="addemp" action="#" method="post">

                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-6 form-group has-feedback">
                                            <input type="text" class="form-control" name="ชื่อชุด" placeholder="ชื่อวัตถุดิบ">
                                        </div>
                                        <div class="col-md-3 col-sm-3 col-xs-6 form-group has-feedback">
                                            <input type="text" class="form-control" name="ราคา" placeholder="หน่วย">
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
                <!-- /Modal Content (ADD MAT)-->         

                <!-- Modal Content (ADD CUST)-->
                <div class="modal fade" id="addCustMenu" role="dialog">
                    <div class="modal-dialog">
                        <!-- เนือหาของ Modal ทั้งหมด -->
                        <div class="modal-content modal-body">
                            <!-- ส่วนหัวของ Modal -->
                            <div class="modal-header">
                                <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">เพิ่มวัตถุดิบ</h4>
                            </div>
                            <!-- ส่วนเนื้อหาของ Modal -->
                            <div class="modal-body ">
                                <form class="form-horizontal form-label-left input_mask" id="addemp" action="#" method="post">

                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-6 form-group has-feedback">
                                            <input type="text" class="form-control" name="ชื่อชุด" placeholder="ชื่อการปรับแต่ง เช่น ความเผ็ด..">
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-6 form-group has-feedback">
                                            <div class="btn-group" data-toggle="buttons">
                                                <label class="btn btn-success" value="1" id="low" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                    <input type="radio" name="#" value="1" required> น้อย
                                                </label>
                                                <label class="btn btn-warning" value="2" id="med" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                    <input type="radio" name="#" value="2" required> ปานกลาง
                                                </label>
                                                <label class="btn btn-danger" value="3" id="high" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                    <input type="radio" name="#" value="3" required> มาก
                                                </label>
                                            </div>
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
                <!-- /Modal Content (ADD CUST)-->             










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
        <script src="handmade/menu.js"></script>
        <script>
                                                                    function readURL(input) {
                                                                        if (input.files && input.files[0]) {
                                                                            var reader = new FileReader();

                                                                            reader.onload = function (e) {
                                                                                $('#blah').attr('src', e.target.result);
                                                                            }

                                                                            reader.readAsDataURL(input.files[0]);
                                                                        }
                                                                    }

                                                                    $("#imgInp").change(function () {
                                                                        readURL(this);
                                                                    });
        </script>
        <style>
            .modal-body-forMenuSet {
                position: relative;
                overflow-y: auto;
                width: 1000px;
                right: 30%;
                height: 700px
            }
        </style>
    </body>
</html>
