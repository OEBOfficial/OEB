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
        <title>ตำแหน่งพนักงาน</title>
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
        <style type="text/css">
            .modal-body-forEmpPos {
                position: relative;
                overflow-y: auto;
                width: 1000px;
                right: 30%;
                height: 700px
            }
            input[type=checkbox][disabled] + label {
                color: #ccc;
            }
            label{
                font-weight:100;
            }
        </style>
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
                                    <a href="javascript:void(0)" data-toggle="modal" data-target="#addEmp" style="font-size:25px;">
                                        <i class="fa fa-plus-circle"></i> เพิ่มตำแหน่ง
                                    </a>
                                    <div class="x_content">
                                        <table id="datatable" class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>ชื่อ</th>

                                                    <th>ตัวเลือก</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${emppos}" var="e" varStatus="vs">
                                                    <tr id="tr${e.positionNo}">
                                                        <td>${e.positionName}</td>
                                                        <td>
                                                            <a href="javascript:void(0)" onclick="setConstraint(${e.positionNo})" data-toggle="modal" data-target="#editEmpPos">
                                                                <i class="fa fa-plus-circle"></i> แก้ไข Position นี้
                                                            </a>
                                                            <a href="javascript:void(0)" onclick="delPosition('${e.positionName}',${e.positionNo})">
                                                                <i class="fa fa-trash"></i> ลบ Position นี้
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- /page content -->
                            <!-- Modal Content (Add Employee Position)-->
                            <div class="modal fade" id="addEmp" role="dialog">
                                <div class="modal-dialog">
                                    <!-- เนือหาของ Modal ทั้งหมด -->
                                    <div class="modal-content modal-body-forEmpPos">
                                        <!-- ส่วนหัวของ Modal -->
                                        <div class="modal-header">
                                            <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">เพิ่มตำแหน่งใหม่</h4>
                                        </div>
                                        <!-- ส่วนเนื้อหาของ Modal -->
                                        <div class="modal-body">
                                            <form data-parsley-validate class="form-horizontal form-label-left" name="myform" id="addemppos" method="POST" action="AddEmpPosServlet">
                                                <input type="hidden" id="hiddensub" name="proportion">
                                                <div class="col-md-5 col-sm-5 col-xs-12 form-group">
                                                    <input type="text" name="empPosName" id="empPos" placeholder="กรุณาใส่ชื่อตำแหน่ง" class="form-control" required></div>
                                                <table class="table">
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="emptype" id="paytype1" value="1" class="emptype"><label for="paytype1"> Full - Time </label>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" id="hour1" value="1" disabled class="paytype paytype1"> <label for="hour1">รายชั่วโมง</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" disabled id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" disabled id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" disabled id="empPos" placeholder="ใส่รายได้ต่อชั่วโมง" class="form-control" required>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="2" disabled id="day1" class="paytype paytype1"> <label for="day1">รายวัน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" disabled id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" disabled id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" disabled id="empPos" placeholder="ใส่รายได้ต่อวัน" class="form-control" required>
                                                            </div>
                                                        </td>
                                                    </tr>
<!--                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="3" disabled id="month1" class="paytype paytype1"> <label for="month1">รายเดือน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" disabled id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" disabled id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" disabled id="empPos" placeholder="ใส่รายได้ต่อเดือน" class="form-control" required>
                                                            </div>
                                                        </td>
                                                    </tr>-->
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="emptype" value="2" id="emptype2" class="emptype"><label for="emptype2"> Part - Time</label>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="1" id="hour2" disabled class="paytype paytype2"> <label for="hour2">รายชั่วโมง</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="empPos" placeholder="ใส่รายได้ต่อชั่วโมง" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="2" id="day2" disabled class="paytype paytype2"> <label for="day2">รายวัน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="empPos" placeholder="ใส่รายได้ต่อวัน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
<!--                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="3" id="month2" disabled class="paytype paytype2"> <label for="month2">รายเดือน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="empPos" placeholder="ใส่รายได้ต่อเดือน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr> -->
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="emptype" value="3" id="emptype3" class="emptype"> <label for="emptype3">Training</label>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="1" id="hour3" disabled class="paytype paytype3"> <label for="hour3">รายชั่วโมง</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="empPos" placeholder="ใส่รายได้ต่อชั่วโมง" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="2" id="day3" disabled class="paytype paytype3"> <label for="day3">รายวัน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="empPos" placeholder="ใส่รายได้ต่อวัน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
<!--                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="3" id="month3" disabled class="paytype paytype3"> <label for="month3">รายเดือน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="empPos" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="empPos" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="empPos" placeholder="ใส่รายได้ต่อเดือน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>                                            -->
                                                </table>                                                
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
                            <!-- /Modal Content (Add Employee Position)-->
                            
                            <!-- Modal Content (Edit Employee Position)-->
                            <div class="modal fade" id="editEmpPos" role="dialog">
                                <div class="modal-dialog">
                                    <!-- เนือหาของ Modal ทั้งหมด -->
                                    <div class="modal-content modal-body-forEmpPos">
                                        <!-- ส่วนหัวของ Modal -->
                                        <div class="modal-header">
                                            <!-- ปุ่มกดปิด (X) ตรงส่วนหัวของ Modal -->
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">แก้ไขตำแหน่ง</h4>
                                        </div>
                                        <!-- ส่วนเนื้อหาของ Modal -->
                                        <div class="modal-body">
                                            <form id="editform" data-parsley-validate class="form-horizontal form-label-left" name="myform" method="POST" action="EditEmpPosServlet">
                                                <input type="hidden" id="edithiddensub" name="proportion">
                                                <input type="hidden" name="empPosNo" id="editEmpPosNo">
                                                <div class="col-md-5 col-sm-5 col-xs-12 form-group">
                                                    <h4 id="editEmpPosName">ตำแหน่ง</h4>
                                                </div>
                                                <table class="table">
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="emptype" id="editpaytype1" value="1" class="editemptype"><label for="editpaytype1"> Full - Time </label>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" id="edithour1" value="1" disabled class="editpaytype editpaytype1"> <label for="edithour1">รายชั่วโมง</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour11" disabled placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour11" disabled placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay11" disabled placeholder="ใส่รายได้ต่อชั่วโมง" class="form-control" required>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="2" disabled id="editday1" class="editpaytype editpaytype1"> <label for="editday1">รายวัน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour12" disabled placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour12" disabled placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay12" disabled placeholder="ใส่รายได้ต่อวัน" class="form-control" required>
                                                            </div>
                                                        </td>
                                                    </tr>
<!--                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="3" disabled id="editmonth1" class="editpaytype editpaytype1"> <label for="editmonth1">รายเดือน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour13" disabled placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour13" disabled placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay13" disabled placeholder="ใส่รายได้ต่อเดือน" class="form-control" required>
                                                            </div>
                                                        </td>
                                                    </tr>-->
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="emptype" value="2" id="editemptype2" class="editemptype"><label for="editemptype2"> Part - Time</label>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="1" id="edithour2" disabled class="editpaytype editpaytype2"> <label for="edithour2">รายชั่วโมง</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour21" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour21" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay21" placeholder="ใส่รายได้ต่อชั่วโมง" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="2" id="editday2" disabled class="editpaytype editpaytype2"> <label for="editday2">รายวัน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour22" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour22" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay22" placeholder="ใส่รายได้ต่อวัน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
<!--                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="3" id="editmonth2" disabled class="editpaytype editpaytype2"> <label for="editmonth2">รายเดือน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour23" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour23" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay23" placeholder="ใส่รายได้ต่อเดือน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr> -->
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="emptype" value="3" id="editemptype3" class="editemptype"> <label for="editemptype3">Training</label>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="1" id="edithour3" disabled class="editpaytype editpaytype3"> <label for="edithour3">รายชั่วโมง</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour31" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour31" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay31"     placeholder="ใส่รายได้ต่อชั่วโมง" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="2" id="editday3" disabled class="editpaytype editpaytype3"> <label for="editday3">รายวัน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour32" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour32" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay32" placeholder="ใส่รายได้ต่อวัน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>
<!--                                                    <tr>
                                                        <td>                                                
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="paytype" value="3" id="editmonth3" disabled class="editpaytype editpaytype3"> <label for="month3">รายเดือน</label>
                                                        </td>
                                                        <td>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="minhour" id="minhour33" placeholder="ทำงานน้อยสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="maxhour" id="maxhour33" placeholder="ทำงานมากสุด(ชั่วโมง)" class="form-control" disabled required></div>
                                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                                <input type="text" name="pay" id="pay33" placeholder="ใส่รายได้ต่อเดือน" class="form-control" disabled required>
                                                            </div>
                                                        </td>
                                                    </tr>                                            -->
                                                </table>                                                
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
                            <!-- /Modal Content (Edit Employee Position)-->
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
        <script src="../handmade/emppos.js"></script>
    </body>
</html>