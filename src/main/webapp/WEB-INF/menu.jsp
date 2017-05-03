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
        <title>จัดการเมนู</title>
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
    <body>
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
                                        <h4>เมนูอาหารแบบเดี่ยว</h4>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="col-lg-12" style="text-align:center">เมนูสาขานี้</div>
                                        <div class="x_content">
                                            <table id="thisbranchmenu" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th class="table-rows">ชื่ออาหาร</th>
                                                        <th class="table-rows">ราคา</th>
                                                        <th class="table-rows">ตัวเลือก</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${menus}" var="m">
                                                        <c:if test="${m.isThisBranchMenu}">
                                                            <tr id="tr${m.menuNo}">
                                                                <td>${m.menuNameTH} / ${m.menuNameEN}</td>
                                                                <td><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${m.menuPrice}"/> ฿</td>
                                                                <c:choose>
                                                                    <c:when test="${m.branchNo == restowner.branchNo}">
                                                                        <td>
                                                                            แก้ไข
                                                                            ลบ
                                                                        </td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td>
                                                                            ดูรายละเอียด + แก้ไขราคา
                                                                        </td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="col-lg-12" style="text-align:center">เมนูสาขาอื่นที่เป็น Official</div>
                                        <div class="x_content">
                                            <table id="otherbranchmenu" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th class="table-rows">ชื่ออาหาร</th>
                                                        <th class="table-rows">ราคา</th>
                                                        <th class="table-rows">ตัวเลือก</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${menus}" var="m">
                                                        <c:if test="${!m.isThisBranchMenu && m.isOfficialMenu == 1}">
                                                            <tr id="tr${m.menuNo}">
                                                                <td>${m.menuNameTH} / ${m.menuNameEN}</td>
                                                                <td><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${m.menuPrice}"/> ฿</td>
                                                                <td><a href="javascript:void(0)" onclick="getMenu(${m.menuNo})">ดูรายละเอียด</a></td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h4>เมนูอาหารแบบเซต</h4>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="col-lg-12" style="text-align:center">เมนูแบบเซตสาขานี้</div>
                                        <div class="x_content">
                                            <table id="thisbranchmenuset" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th class="table-rows">ชื่อเซตอาหาร</th>
                                                        <th class="table-rows">ราคา</th>
                                                        <th class="table-rows">ตัวเลือก</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${menusets}" var="ms">
                                                        <c:if test="${ms.isThisBranchMenu}">
                                                            <tr id="trthis${ms.menuSetNo}">
                                                                <td>${ms.menuSetNameTH} / ${ms.menuSetNameEN}</td>
                                                                <td><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${ms.menuSetPrice}"/> ฿</td>
                                                                <c:choose>
                                                                    <c:when test="${ms.branchNo == restowner.branchNo}">
                                                                        <td>
                                                                            <a href="javascript:void(0)" onclick="getMenuByMenuSetForEdit(${ms.menuSetNo})">แก้ไข</a>
                                                                            <a href="javascript:void(0)" onclick="delMenuSet(${ms.menuSetNo})">ลบ</a>
                                                                        </td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td>
                                                                            ดูรายละเอียด + แก้ไขราคา
                                                                        </td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="col-lg-12" style="text-align:center">เมนูแบบเซตสาขาอื่นที่เป็น Official</div>
                                        <div class="x_content">
                                            <table id="otherbranchmenuset" class="table table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th class="table-rows">ชื่อเซตอาหาร</th>
                                                        <th class="table-rows">ราคา</th>
                                                        <th class="table-rows">ตัวเลือก</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${menusets}" var="ms">
                                                        <c:if test="${!ms.isThisBranchMenu && ms.isOfficialMenuSet == 1}">
                                                            <tr id="tr${ms.menuSetNo}">
                                                                <td>${ms.menuSetNameTH} / ${ms.menuSetNameEN}</td>                                                                
                                                                <td><fmt:formatNumber type="number" pattern="#,###,##0.00" value="${ms.menuSetPrice}"/> ฿</td>
                                                                <td><a href="javascript:void(0)" onclick="getMenuByMenuSet(${ms.menuSetNo})">ดูรายละเอียด</a></td>
                                                            </tr>
                                                        </c:if>
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
                <script src="../handmade/menu.js"></script>
                </body>
                </html>
