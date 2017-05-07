$(document).ready(function () {
    $("#thisbranchmenu").DataTable({
        'order': [[1, 'asc']],
        'columnDefs': [
            {orderable: false, targets: [0]}
        ]
    });

    $("#otherbranchmenu").DataTable({
        'order': [[1, 'asc']],
        'columnDefs': [
            {orderable: false, targets: [0]}
        ]
    });
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
//                        swal("เรียบร้อย", "เมนูอาหารถูกลบออกเรียบร้อยแล้ว", "success");
                        location.reload();
                    }
                });
            });
}


function showMenu(menuNo, showType) {
    $.ajax({
        type: "POST",
        url: "SetMenuMaterialAjaxServlet",
        dataType: "json",
        data: "menuNo=" + encodeURIComponent(menuNo),
        success: function (json) {
            if (showType === 2 || showType === 3) {
                var showsetdtb = $("#showmenutable").DataTable();
                showsetdtb.clear();
                $(".showmenuname").html(json.menuinfo.menuNameTH + " / " + json.menuinfo.menuNameEN);
                $("#showmenuprice").html(json.menuinfo.menuPrice);
                $("#showmenudesc").html(json.menuinfo.menuDesc);
                $("#showmenupic").attr('src', json.menuinfo.menuPicPath === 'null' ? 'https://scontent.fbkk14-1.fna.fbcdn.net/v/t34.0-12/18360467_674544166065326_1398146494_n.png?oh=ccb78e42d450920d59b901c35e9c33ad&oe=5911167A' : json.menuinfo.menuPicPath);
                $("#showmenuno").val(json.menuinfo.menuNo);
                for (i = 0; i < json.material.length; i++) {
                    var m = json.material[i];
                    showsetdtb.row.add({
                        0: m.matName ,
                        1: m.quantity + " " + m.unit
                    });
                }
                showsetdtb.draw();
                if (showType === 2) {
                    $("#showmenufooter").css('display', 'none');
                }
            }
        }
    });
}

function confirmmenuset() {
    swal({
        title: "เพิ่มเมนูเข้าสาขาใช่หรือไม่",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#169F85",
        cancelButtonText: "ยกเลิก",
        confirmButtonText: "ตกลง",
        closeOnConfirm: false
    }, function () {
        $("#addmenutobranchservlet").submit();
    }
    );
}