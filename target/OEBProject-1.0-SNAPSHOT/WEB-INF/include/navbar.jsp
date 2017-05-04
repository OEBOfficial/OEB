<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="#" class="site_title">
                <img src="images/OEB_LOGO.png" style="width:50px;margin-right:5px;" alt="...">
                <span>Order Eat Bill</span>
            </a>
        </div>
        <div class="clearfix"></div>
        <!-- menu profile quick info -->
        <div class="profile clearfix">
            <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
            </div>
            <div class="profile_info">
                <span>ยินดีต้อนรับ,</span>
                <h2>${restowner.restUserName}</h2>
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
                            <li><a id="empdata" href="ToEmpServlet">ข้อมูลพนักงาน</a></li>
                            <li><a id="emppos" href="ToEmpPosServlet">ตำแหน่งพนักงาน</a></li>
                            <li><a id="workhist" href="ToEmpWorkHistServlet">ประวัติการทำงาน</a></li>
                            <li><a id="emppaid" href="ToEmpPaidServlet">จ่ายเงินพนักงาน</a></li>
                            <li><a id="empcheck" href="ToEmpCheckServlet">เช็คชื่อพนักงาน</a></li>
                        </ul>
                    </li>
                    <li>
                        <a id="menutab"><i class="fa fa-cutlery"></i> จัดการเมนูอาหาร <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a id="menu" href="ToMenuServlet">เมนูอาหารแบบเดี่ยว</a></li>
                            <li><a id="menuset" href="ToMenuSetServlet">เมนูอาหารแบบชุด</a></li>
                            <li><a id="menutype" href="ToMenuTypeServlet">ประเภทเมนูอาหาร</a></li>
                            <li><a id="menucust" href="ToMenuCustServlet">ปรับแต่งเมนูอาหาร</a></li>
                        </ul>
                    </li>
                    <li>
                        <a><i class="fa fa-spoon"></i> จัดการวัตถุดิบอาหาร <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="ToMaterial">ข้อมูลวัตถุดิบ</a></li>
                            <li><a href="ToMaterialType">ประเภทวัตถุดิบ</a></li>
                            <li><a href="ToMaterialStock">สต็อกวัตถุดิบ</a></li>
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
            <a data-toggle="tooltip" data-placement="top" title="Logout" href="LogoutServlet">
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
                        <img src="images/img.jpg" alt="">${restowner.restUserName}
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
                        <li><a href="LogoutServlet"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
<!-- /top navigation -->