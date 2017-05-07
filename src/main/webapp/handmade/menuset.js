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
    $("#editmenuset").DataTable({
        'order': [[1, 'asc']],
        'columnDefs': [
            {orderable: false, targets: [0]}
        ]
    });
});

$(".choosemenu").click(function () {
    var amountinput = $(this).parent().parent().find("td:last-child").children();
    var isCheck = $(this).is(':checked');
    if (!isCheck) {
        amountinput.attr('disabled', true);
    } else {
        amountinput.attr('disabled', false);
    }
});

$(".editchoosemenu").click(function () {
    var amountinput = $(this).parent().parent().find("td:last-child").children();
    var isCheck = $(this).is(':checked');
    if (!isCheck) {
        amountinput.attr('disabled', true);
    } else {
        amountinput.attr('disabled', false);
    }
});

function getMenuByMenuSet(menuSetNo, showType) {
    $.ajax({
        type: "POST",
        url: "SetMenuSetAjaxServlet",
        dataType: "json",
        data: "menuSetNo=" + encodeURIComponent(menuSetNo),
        success: function (json) {
            if (showType === 1){
                $(".editMenuSetName").html(json.menusetinfo.menuSetNameTH + " / " + json.menusetinfo.menuSetNameEN);
                $(".editMenuSetNameTH").val(json.menusetinfo.menuSetNameTH);
                $(".editMenuSetNameEN").val(json.menusetinfo.menuSetNameEN);
                $("#editDesc").val(json.menusetinfo.menuSetDesc);
                $("#editPic").attr('src', json.menusetinfo.menuSetPicPath === 'null' ? 'https://scontent.fbkk14-1.fna.fbcdn.net/v/t34.0-12/18360467_674544166065326_1398146494_n.png?oh=ccb78e42d450920d59b901c35e9c33ad&oe=5911167A' : json.menusetinfo.menuSetPicPath);
                $("#editPrice").val(json.menusetinfo.menuSetPrice);
                $("#editOfficialMenuSet").prop('checked',json.menusetinfo.isOfficialMenuSet==1);
                $("#editAvailable").prop('checked',false);
                $(".editchoosemenu").prop('checked',false);
                $(".editmenuamount").attr('disabled',true);
                $(".editmenuamount").val('');
                $("#editMenuSetNo").val(menuSetNo);
                for(i = 0; i < json.menuset.length; i++){
                    var ms = json.menuset[i];
                    $("#editmenuamount"+ms.menuNo).val(ms.amount);
                    $("#editmenuamount"+ms.menuNo).attr('disabled', false);
                    $("#choose"+ms.menuNo).prop('checked', true);
                }
            }else if (showType === 2 || showType === 3) {
                var fromPrice = 0;
                var showsetdtb = $("#showsetdatatable").DataTable();
                showsetdtb.clear();
                for (i = 0; i < json.menuset.length; i++) {
                    var ms = json.menuset[i];
                    showsetdtb.row.add({
                        0: ms.menuNameTH + " / " + ms.menuNameEN,
                        1: ms.menuPrice + " ฿",
                        2: ms.amount
                    });
                    fromPrice += ms.menuPrice * ms.amount;
                }
                showsetdtb.draw();
                $("#showsetfromprice").html(fromPrice);
                $("#showsettoprice").html(json.menusetinfo.menuSetPrice);
                $("#showsetname").html(json.menusetinfo.menuSetNameTH + " / " + json.menusetinfo.menuSetNameEN);
                $("#showsetmenusetno").val(json.menusetinfo.menuSetNo);
                $("#showsetdesc").html(json.menusetinfo.menuSetDesc);
                $("#showsetpic").attr('src', json.menusetinfo.menuSetPicPath === 'null' ? 'https://scontent.fbkk14-1.fna.fbcdn.net/v/t34.0-12/18360467_674544166065326_1398146494_n.png?oh=ccb78e42d450920d59b901c35e9c33ad&oe=5911167A' : json.menusetinfo.menuSetPicPath);
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

function checkInput() {
    var pass = true;
    $(".menuamount").each(function () {
        if ($(this).attr('disabled') !== 'disabled') {
            if($(this).val() === '0' || $(this).val() === '' || $(this).val() < 0){
                pass = false;
            }
        }
    });
    if(pass){
        $("#addmenuset").submit();
    }else{
        alert('ค่าจำนวนเป็นค่าว่าง 0 หรือน้อยกว่า');
    }
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