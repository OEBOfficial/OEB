$(document).ready(function () {
    $("#thisbranchmenuset").DataTable({
        'order': [[1, 'asc']],
        'columnDefs': [
            {orderable: false, targets: [0]}
        ]
    });
    $("#otherbranchmenuset").DataTable({
        'order': [[1, 'asc']],
        'columnDefs': [
            {orderable: false, targets: [0]}
        ]
    });
});

function getMenuByMenuSet(menuSetNo, showType) {
    $.ajax({
        type: "POST",
        url: "SetMenuSetAjaxServlet",
        dataType: "json",
        data: "menuSetNo=" + encodeURIComponent(menuSetNo),
        success: function (json) {
            if (showType === 2 || showType === 3) {
                var fromPrice = 0;
                var showsetdtb = $("#showsetdatatable").DataTable();
                showsetdtb.clear();
                for (i = 0; i < json.menuset.length; i++) {
                    var ms = json.menuset[i];
                    showsetdtb.row.add({
                        0: ms.menuPicPath === 'null' ? '<center><img id="showsetpic" src="https://img.clipartfest.com/1c20817e0b1203f771effa178ccc6b66_cloud-upload-2-icon-upload-clipart_512-512.png" style="width:50px;"  alt="your image"  class="img-thumbnail" /></center>'
                                : '<center><img id="showsetpic" src="' + ms.menuPicPath + '" style="width:50px;"  alt="your image"  class="img-thumbnail" /></center>',
                        1: ms.menuNameTH + " / " + ms.menuNameEN,
                        2: ms.menuPrice + " ฿",
                        3: ms.amount
                    });
                    fromPrice += ms.menuPrice * ms.amount;
                }
                showsetdtb.draw();
                $("#showsetfromprice").html(fromPrice);
                $("#showsettoprice").html(json.menusetinfo.menuSetPrice);
                $("#showsetname").html(json.menusetinfo.menuSetNameTH + " / " + json.menusetinfo.menuSetNameEN);
                $("#showsetmenusetno").val(json.menusetinfo.menuSetNo);
                $("#showsetpic").attr('src', json.menusetinfo.menuSetPicPath === 'null' ? 'https://img.clipartfest.com/1c20817e0b1203f771effa178ccc6b66_cloud-upload-2-icon-upload-clipart_512-512.png' : json.menusetinfo.menuSetPicPath);
                $("#showsetfooter").css('display', 'block');
                if (showType === 2) {
                    $("#showsetfooter").css('display', 'none');
                }
            }
        }
    });
}

function confirmmenuset() {
    swal({
        title: "หากคุณต้องการเพิ่มชุดเมนูนี้",
        text: "ต้องเพิ่ม \"เมนู\" ที่อยู่ใน \"ชุดเมนู\" นี้ เข้าสาขาของคุณด้วย",
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
//                        swal("เรียบร้อย", "เซตอาหารถูกลบออกเรียบร้อยแล้ว", "success");
//                        $("#thisbranchmenuset").DataTable().row("#trthis" + menuSetNo).remove().draw();
                        location.reload();
                    }
                });
            });
}