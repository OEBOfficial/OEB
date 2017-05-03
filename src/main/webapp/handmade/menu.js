$(document).ready(function () {
    $("#menutab").parent().addClass('active');
    $("#menutab").parent().find('ul').css('display', 'block');
    $("#menu").parent().addClass('active');
    $("#thisbranchmenu").DataTable();
    $("#otherbranchmenu").DataTable();
    $("#thisbranchmenuset").DataTable();
    $("#otherbranchmenuset").DataTable();
});

function getMenuByMenuSet(menuSetNo) {
    $.ajax({
        type: "POST",
        url: "SetMenuSetAjaxServlet",
        dataType: "json",
        data: "menuSetNo=" + encodeURIComponent(menuSetNo),
        success: function (json) {
            for (i = 0; i < json.menuset.length; i++) {
                var ms = json.menuset[i];
                alert('menuNo : ' + ms.menuNo);
            }
        }
    });
}

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

function getMenuByMenuSetForEdit(menuSetNo) {
    $.ajax({
        type: "POST",
        url: "SetMenuSetAjaxServlet",
        dataType: "json",
        data: "menuSetNo=" + encodeURIComponent(menuSetNo),
        success: function (json) {
            for (i = 0; i < json.menuset.length; i++) {
                var ms = json.menuset[i];
                alert('menuNo : ' + ms.menuNo);
            }
        }
    });
}

function delMenuSet(menuSetNo) {
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
                    url: "DelMenuSetAjaxServlet",
                    dataType: "text",
                    data: "menuSetNo=" + encodeURIComponent(menuSetNo),
                    success: function (result) {
                        swal("เรียบร้อย", "เซตอาหารถูกลบออกเรียบร้อยแล้ว", "success");
                        $("#thisbranchmenuset").DataTable().row("#trthis" + menuSetNo).remove().draw();
                    }
                });
            });
}