$(document).ready(function () {
    $("#menutab").parent().addClass('active');
    $("#menutab").parent().find('ul').css('display', 'block');
    $("#menutype").parent().addClass('active');
});

function delMenuType(menuTypeNo) {
    swal({
        title: "คุณต้องการลบใช่หรือไม่ ?",
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
                    url: "DelMenuTypeAjaxServlet",
                    dataType: "text",
                    data: "menuTypeNo=" + encodeURIComponent(menuTypeNo),
                    success: function (result) {
                        swal("เรียบร้อย", "ข้อมูลประเภทอาหารถูกลบแล้ว", "success");
                        $("#datatable").DataTable().row("#tr" + menuTypeNo).remove().draw();
                    }
                });
            });
}

function setMenuTypeName(menuTypeNo) {
    $(".editMenuTypeName").html($("#menuTypeNo" + menuTypeNo).text());
    $(".editMenuTypeName").val($("#menuTypeNo" + menuTypeNo).text());
    $("#editMenuTypeNo").val(menuTypeNo);
}