$(document).ready(function () {
    $("#thisbranchmenu").DataTable();
    $("#otherbranchmenu").DataTable();
});

function getMenu(menuNo) {
    $.ajax({
        type: "POST",
        url: "SetMenuAjaxServlet",
        dataType: "json",
        data: "menuNo=" + encodeURIComponent(menuNo),
        success: function (json) {
            alert(json.menuNameTH + " ราคา " + json.menuPrice + " บาท");
        }
    });
}

function delMenu(menuNo) {
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
                    type: "POST",
                    url: "DelMenuAjaxServlet",
                    dataType: "text",
                    data: "menuNo=" + encodeURIComponent(menuNo),
                    success: function (result) {
                        swal("เรียบร้อย", "เมนูอาหารถูกลบออกเรียบร้อยแล้ว", "success");
                        $("#thisbranchmenu").DataTable().row("#trthis" + menuNo).remove().draw();
                    }
                });
            });
}