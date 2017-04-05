$(document).ready(function () {
    $("#emptab").parent().addClass('active');
    $("#emptab").parent().find('ul').css('display', 'block');
    $("#empdata").parent().addClass('active');
});

function editEmp(empNo) {
    $.ajax({
        type: "POST",
        url: "SetEmpAjaxServlet",
        dataType: "json",
        data: "empno=" + encodeURIComponent(empNo),
        success: function (json) {
            $("#editSubmit").val(json.empNo);
            $("#name").val(json.empName);
            if (json.gender === 'M') {
                $("#gender-m").click();
            } else {
                $("#gender-f").click();
            }
            $("#telno").val(json.telNo);
            $("#empPos").val(json.positionNo);
            $("#empPos").trigger("change");
            $(".editEmpTypeItem[value=" + json.empTypeNo + "]").click();
            $("#editPayTypeItem").val(json.payTypeNo);
            $("#editPayTypeItem").trigger("change");

            if (json.specPay == "null") {
                $("#general").click();
                $("#specpay").val('');
            } else {
                $("#special").click();
                $("#specpay").val(json.specPay);
            }
        }
    });
}

function delEmp(empNo, empName) {
    swal({
        title: "คุณต้องการลบใช่หรือไม่ ?",
        text: "คุณจะไม่สามารถกู้ข้อมูล " + empName + " กลับมาได้อีก!",
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
                    url: "DelEmpAjaxServlet",
                    dataType: "text",
                    data: "empno=" + encodeURIComponent(empNo),
                    success: function (result) {
                        swal("เรียบร้อย", "ข้อมูลพนักงานถูกลบแล้ว", "success");
                        $("#datatable").DataTable().row("#tr" + empNo).remove().draw();
                    }
                });
            });
}

$("#addEmpPos").change(function () {
    $(".empTypeItem").children().attr('checked', false);
    $(".empTypeItem").attr('disabled', true);
    $(".empTypeItem").removeClass('active');
    $("#payTypeItem").attr('disabled', true);
    $("#payTypeItem").html("<option value>---เลือกประเภทการจ่าย---</option>");
    $(".payMethod").children().attr('checked', false);
    $(".payMethod").removeClass('active');
    $(".payMethod").attr('disabled', true);
    $("#addspecpay").val('');
    $("#addspecpay").attr('disabled', true);
    var val = $(this).val();
    var empTypeNo = empTypeConstraint[val];
    for (var i = 0; i < empTypeNo.length; i++) {
        $("#" + empTypeNo[i]).attr('disabled', false);
    }
});

$(".empTypeItem").click(function () {
    if ($(this).attr('disabled') === 'disabled') {
        return false;
    } else {
        $("#payTypeItem").children().remove();
        $("#payTypeItem").html("<option value>---เลือกประเภทการจ่าย---</option>");
        $("#payTypeItem").attr('disabled', false);
        $(".payMethod").children().attr('checked', false);
        $(".payMethod").removeClass('active');
        $(".payMethod").attr('disabled', true);
        $("#addspecpay").val('');
        $("#addspecpay").attr('disabled', true);
        var payTypeNo = payTypeConstraint[$("#addEmpPos").val() + "|" + $(this).attr("value")];
        for (var i = 0; i < payTypeNo.length; i++) {
            if (payTypeNo[i] === "1") {
                $("#payTypeItem").append("<option value='1'>ชั่วโมง</option>");
            } else if (payTypeNo[i] === "2") {
                $("#payTypeItem").append("<option value='2'>วัน</option>");
            }
        }
    }
});

$("#payTypeItem").change(function () {
    $(".payMethod").children().attr('checked', false);
    $(".payMethod").removeClass('active');
    $("#addspecpay").val('');
    $("#addspecpay").attr('disabled', true);
    if ($(this).val() != '') {
        $(".payMethod").attr('disabled', false);
        $("#addspecpay").attr('placeholder', 'ค่าจ้าง (บาท/' + $(this).find("option:selected").text() + ')');
    } else {
        $(".payMethod").attr('disabled', true);
        $("#addspecpay").attr('placeholder', 'ค่าจ้าง');
    }
});

$("#addspecial").click(function () {
    $("#addspecpay").attr('disabled', false);
});

$("#addgeneral").click(function () {
    $("#addspecpay").val('');
    $("#addspecpay").attr('disabled', true);
});

//for edit-------------------------------------------------------------------------------------

$("#empPos").change(function () {
    $(".editEmpTypeItem").children().attr('checked', false);
    $(".editEmpTypeItem").attr('disabled', true);
    $(".editEmpTypeItem").removeClass('active');
    $("#editPayTypeItem").attr('disabled', true);
    $("#editPayTypeItem").html("<option value>---เลือกประเภทการจ่าย---</option>");
    $(".editPayMethod").children().attr('checked', false);
    $(".editPayMethod").removeClass('active');
    $(".editPayMethod").attr('disabled', true);
    $("#specpay").val('');
    $("#specpay").attr('disabled', true);
    var val = $(this).val();
    var empTypeNo = empTypeConstraint[val];
    for (var i = 0; i < empTypeNo.length; i++) {
        $("#e" + empTypeNo[i]).attr('disabled', false);
    }
});

$(".editEmpTypeItem").click(function () {
    if ($(this).attr('disabled') === 'disabled') {
        return false;
    } else {
        $("#editPayTypeItem").children().remove();
        $("#editPayTypeItem").html("<option value>---เลือกประเภทการจ่าย---</option>");
        $("#editPayTypeItem").attr('disabled', false);
        $(".editPayMethod").children().attr('checked', false);
        $(".editPayMethod").removeClass('active');
        $(".editPayMethod").attr('disabled', true);
        $("#specpay").val('');
        $("#specpay").attr('disabled', true);
        var payTypeNo = payTypeConstraint[$("#empPos").val() + "|" + $(this).attr("value")];
        for (var i = 0; i < payTypeNo.length; i++) {
            if (payTypeNo[i] === "1") {
                $("#editPayTypeItem").append("<option value='1'>ชั่วโมง</option>");
            } else if (payTypeNo[i] === "2") {
                $("#editPayTypeItem").append("<option value='2'>วัน</option>");
            }
        }
    }
});

$("#editPayTypeItem").change(function () {
    $(".editPayMethod").children().attr('checked', false);
    $(".editPayMethod").removeClass('active');
    $("#specpay").val('');
    $("#specpay").attr('disabled', true);
    if ($(this).val() != '') {
        $(".editPayMethod").attr('disabled', false);
        $("#specpay").attr('placeholder', 'ค่าจ้าง (บาท/' + $(this).find("option:selected").text() + ')');
    } else {
        $(".editPayMethod").attr('disabled', true);
        $("#specpay").attr('placeholder', 'ค่าจ้าง');
    }
});

$("#special").click(function () {
    $("#specpay").attr('disabled', false);
});

$("#general").click(function () {
    $("#specpay").val('');
    $("#specpay").attr('disabled', true);
});

$(".btn").click(function () {
    if ($(this).attr('disabled') === 'disabled') {
        return false;
    }
});