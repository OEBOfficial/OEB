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
        <title>จัดการชุดเมนู</title>
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
                                    <h2>เมนูอาหารแบบชุดของสาขานี้</h2>          
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <form id="thisbranchmenusetoption" action="ManageMenuSetServlet" method="POST">
                                        <p>
                                            <a href="javascript:void(0)" data-toggle="modal" data-target="#addSetMenu"  class="btn btn-success btn-sm"><i class="fa fa-plus-circle"></i>&nbsp; เพิ่มชุดเมนู</a>
                                            <button type="submit" name="submit" value="del" href="javascript:void(0)" class="btn btn-danger btn-sm"><i class="fa fa-minus-square"></i>&nbsp; ลบที่เลือก</button>
                                            <button type="submit" name="submit" value="open" href="javascript:void(0)" class="btn btn-info btn-sm"><i class="fa fa-check-circle"></i>&nbsp; เปิดที่เลือก</button>
                                            <button type="submit" name="submit" value="close" href="javascript:void(0)" class="btn btn-info btn-sm"><i class="fa fa-times-circle"></i>&nbsp; ปิดที่เลือก</button>
                                        </p>
                                        <table id="datatable-checkbox" class="table table-striped table-bordered bulk_action">
                                            <thead>
                                                <tr>
                                                    <th><input type="checkbox" class="flat"> &nbsp; เลือก</th>
                                                    <th>ชื่อชุดอาหาร</th>
                                                    <th>ราคา</th>
                                                    <th>สถานะ</th>
                                                    <th>ตัวเลือก</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach items="${menusets}" var="ms">
                                                    <c:if test="${ms.isThisBranchMenu}">
                                                        <tr id="trthis${ms.menuSetNo}">
                                                            <td>
                                                                <input type="checkbox" name="checkforedit" value="${ms.menuSetNo}" class="flat">
                                                            </td>
                                                            <td valign="center">${ms.menuSetNameTH} / ${ms.menuSetNameEN}</td>
                                                            <td valign="center"><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${ms.menuSetPrice}"/> ฿</td>
                                                            <td valign="center">${ms.isAvailable?'<i class="fa fa-check-circle"></i>':'<i class="fa fa-times-circle"></i>'}</td>
                                                            <c:choose>
                                                                <c:when test="${ms.branchNo == restowner.branchNo}">
                                                                    <td valign="center">
                                                                        <a href="javascript:void(0)" onclick="getMenuByMenuSet(${ms.menuSetNo})" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#editSet">
                                                                            <i class="fa fa-pencil-square"></i>&nbsp; แก้ไข
                                                                        </a>
                                                                        <a href="javascript:void(0)" onclick="delMenuSet(${ms.menuSetNo})" class="btn btn-danger btn-sm"><i class="fa fa-minus-square"></i>&nbsp; ลบ</a>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td valign="center">
                                                                        <a href="javascript:void(0)" onclick="getMenuByMenuSet(${ms.menuSetNo}, 2)" data-toggle="modal" data-target="#showset"  class="btn btn-dark btn-sm">
                                                                            <i class="fa fa-search"></i>&nbsp; ดูรายละเอียด
                                                                        </a>
                                                                        <a href="javascript:void(0)" onclick="delMenuSet(${ms.menuSetNo})" class="btn btn-danger btn-sm"><i class="fa fa-minus-square"></i>&nbsp; ลบ</a>
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
                                    <h2>เมนูอาหารแบบชุดของสาขาอื่น</h2>          
                                    <h6>
                                        &nbsp;&nbsp;<i>*สามารถเลือกอาหารชุด เพื่อนำไปใช้กับสาขาอื่นได้</i>
                                    </h6>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <form action="ManageMenuSetServlet" method="POST">
                                        <p>
                                            <button type="addtobranch" class="btn btn-success btn-sm" name="submit" value="addtobranch"><i class="fa fa-arrow-up"></i>&nbsp; เพิ่มเข้าสาขา</button>
                                        </p>  
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>เลือก</th>
                                                    <th>ชื่อชุดอาหาร</th>
                                                    <th>ราคา</th>
                                                    <th>ตัวเลือก</th>
                                                </tr>
                                            </thead>


                                            <tbody>
                                                <c:forEach items="${menusets}" var="ms">
                                                    <c:if test="${!ms.isThisBranchMenu && ms.isOfficialMenuSet == 1}">
                                                        <tr id="tr${ms.menuSetNo}">
                                                            <td>
                                                                <input type="checkbox" name="checkforedit" value="${ms.menuSetNo}" class="flat">
                                                            </td>
                                                            <td><a href="javascript:void(0)" onclick="getMenuByMenuSet(${ms.menuSetNo}, 3)" data-toggle="modal" data-target="#showset" >${ms.menuSetNameTH} / ${ms.menuSetNameEN}</a></td>                                                                
                                                            <td><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${ms.menuSetPrice}"/> ฿</td>
                                                            <td valign="center">
                                                                <a href="javascript:void(0)" onclick="getMenuByMenuSet(${ms.menuSetNo}, 3)" data-toggle="modal" data-target="#showset"  class="btn btn-dark btn-sm">
                                                                    <i class="fa fa-search"></i>&nbsp; ดูรายละเอียด
                                                                </a>
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
            </div>
        </div>


        <!-- Modal Content (ADD SET)-->
        <div class="modal fade" id="addSetMenu" role="dialog">
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
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">

                                    <input type="text" class="form-control" name="ชื่อชุด" placeholder="ชื่อชุดเมนู">

                                </div>

                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>รายการอาหารภายในชุด</h2>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <table id="datatable-fixed-header" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>เลือก</th>
                                                        <th>ชื่ออาหาร</th>
                                                        <th>ราคา</th>
                                                        <th>รายละเอียด</th>
                                                    </tr>
                                                </thead>


                                                <tbody>
                                                    <tr>
                                                        <td class="a-center ">
                                                            <input type="checkbox" class="flat" name="table_records">
                                                        </td>
                                                        <td>System Architect</td>
                                                        <td>Edinburgh</td>
                                                        <td>61</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3 col-sm-3 col-xs-3 form-group has-feedback">
                                    <div class="form-group">    
                                        <input type="text" class="form-control" name="price" placeholder="ราคา">
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
        <!-- /Modal Content (ADD SET)--> 

        <!-- Modal Content (SHOW SET OFFICIAL)-->
        <div class="modal fade" id="showset" role="dialog">
            <div class="modal-dialog">
                <!-- เนือหาของ Modal ทั้งหมด -->
                <div class="modal-content modal-body-forMenuSet">
                    <!-- ส่วนหัวของ Modal -->
                    <div class="modal-header">
                        <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">รายละเอียดของชุด <span id="showsetname">ชื่อเมนูแบบชุด</span>
                            <br> ราคาจาก <span id="showsetfromprice">เท่านั้น</span> บาท เหลือ <span id="showsettoprice">เท่านี้</span> บาท</h4>
                    </div>
                    <!-- ส่วนเนื้อหาของ Modal -->
                    <div class="modal-body ">
                        <form class="form-horizontal form-label-left input_mask" action="AddMenuSetToBranchServlet" id="addmenutobranchservlet" method="post">
                            <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
                                <center><img id="showsetpic" src="https://img.clipartfest.com/1c20817e0b1203f771effa178ccc6b66_cloud-upload-2-icon-upload-clipart_512-512.png" style="width: 250px;height: 250px"  alt="your image"  class="img-thumbnail" /></center>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>รายการอาหารภายในชุด</h2>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <table id="showsetdatatable" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>รูปอาหาร</th>
                                                        <th>ชื่ออาหาร</th>
                                                        <th>ราคา</th>
                                                        <th>จำนวน</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                <center><img id="showsetpic" src="https://img.clipartfest.com/1c20817e0b1203f771effa178ccc6b66_cloud-upload-2-icon-upload-clipart_512-512.png" style="width:50px;"  alt="your image"  class="img-thumbnail" /></center>
                                                </td>
                                                <td>Edinburgh</td>
                                                <td>61</td>
                                                <td>3</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer" id="showsetfooter">
                                <!-- ปุ่มกดปิด (Close) ตรงส่วนล่างของ Modal -->
                                <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                    <input type="checkbox" name="isAvailable" value="1"> เปิดบริการชุดเมนูนี้โดยทันที 
                                    <button type="button" onclick="confirmmenuset()" class="btn btn-success btn-sm"><i class="fa fa-arrow-up"></i>&nbsp; เพิ่มเข้าสาขา</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                                </div>
                            </div>
                            <input type="hidden" id="showsetmenusetno" name="menuSetNo">
                        </form>

                    </div>
                </div>
            </div>
        </div>
        <!-- /Modal Content (SHOW SET OFFICIAL)-->            

        <!-- Modal Content (EDIT SET)-->
        <div class="modal fade" id="editSet" role="dialog">
            <div class="modal-dialog">
                <!-- เนือหาของ Modal ทั้งหมด -->
                <div class="modal-content modal-body-forMenuSet">
                    <!-- ส่วนหัวของ Modal -->
                    <div class="modal-header">
                        <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">รายละเอียดของ *ชื่อชุด*</h4>
                    </div>
                    <!-- ส่วนเนื้อหาของ Modal -->
                    <div class="modal-body ">
                        <form class="form-horizontal form-label-left input_mask" id="addemp" action="#" method="post">
                            <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
                                <center><img id="blah" src="https://img.clipartfest.com/1c20817e0b1203f771effa178ccc6b66_cloud-upload-2-icon-upload-clipart_512-512.png" style="width: 250px;height: 250px"  alt="your image"  class="img-thumbnail" />
                                    <input type='file' id="imgInp" /></center>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                    <div class="form-group">    
                                        <input type="text" class="form-control" name="ชื่อชุด" placeholder="ชื่อชุดเมนู">
                                    </div>
                                </div>

                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>รายการอาหารภายในชุด</h2>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <table id="datatable-fixed-header" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>ชื่ออาหาร</th>
                                                        <th>ราคา</th>
                                                        <th>รายละเอียด</th>
                                                        <th>ตัวเลือก</th>

                                                    </tr>
                                                </thead>


                                                <tbody>
                                                    <tr>
                                                        <td>System Architect</td>
                                                        <td>Edinburgh</td>
                                                        <td>61</td>
                                                        <td>
                                                            <a href="javascript:void(0)" onclick="#" class="btn btn-danger btn-sm"><i class="fa fa-minus-square"></i>&nbsp;ลบ</a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3 col-sm-3 col-xs-12 form-group has-feedback">
                                    <div class="form-group">    
                                        <input type="text" class="form-control" name="price" placeholder="ราคา">
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
        <!-- /Modal Content (EDIT SET)-->      














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
        <script src="handmade/menuset.js"></script>
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