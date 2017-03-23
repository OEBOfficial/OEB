$(document).ready(function () {
    $("#emptab").parent().addClass('active');
    $("#emptab").parent().find('ul').css('display', 'block');
    $("#empdata").parent().addClass('active');
});
//$("#empdata").click(function () {
//    $("#empdata").parent().toggleClass('active');
//});


$("#1").click(function () {
    $("#empType").attr("disabled", false);
    $("#empType option:nth-child(1)").val('1');
    $("#empType option:nth-child(2)").val('2');
    $("#empType option:nth-child(3)").val('3');
});
$("#2").click(function () {
    $("#empType").attr("disabled", false);
    $("#empType option:nth-child(1)").val('4');
    $("#empType option:nth-child(2)").val('5');
    $("#empType option:nth-child(3)").val('6');
});
$("#3").click(function () {
    $("#empType").attr("disabled", false);
    $("#empType option:nth-child(1)").val('7');
    $("#empType option:nth-child(2)").val('8');
    $("#empType option:nth-child(3)").val('9');
});
$("#e1").click(function () {
    $("#eempType").attr("disabled", false);
    $("#eempType option:nth-child(1)").val('1');
    $("#eempType option:nth-child(2)").val('2');
    $("#eempType option:nth-child(3)").val('3');
});
$("#e2").click(function () {
    $("#eempType").attr("disabled", false);
    $("#eempType option:nth-child(1)").val('4');
    $("#eempType option:nth-child(2)").val('5');
    $("#eempType option:nth-child(3)").val('6');
});
$("#e3").click(function () {
    $("#eempType").attr("disabled", false);
    $("#eempType option:nth-child(1)").val('7');
    $("#eempType option:nth-child(2)").val('8');
    $("#eempType option:nth-child(3)").val('9');
});

$("#addspecial").click(function () {
    $("#addspecpay").attr('disabled', false);
});

$("#addgeneral").click(function () {
    $("#addspecpay").val('');
    $("#addspecpay").attr('disabled', true);
});

$("#special").parent().click(function () {
    $("#specpay").attr('disabled', false);
});

$("#general").parent().click(function () {
    $("#specpay").val('');
    $("#specpay").attr('disabled', true);
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
            if (json.gender == 'M') {
                $("#gender-m").click();
            } else {
                $("#gender-f").click();
            }
            $("#telno").val(json.telNo);
            $("#empPos").val(json.positionNo);
            $("#empType").val(json.empTypeNo);
            var empTypeNo = json.empTypeNo;
            if (empTypeNo <= 3) {
                $("#e1").click();
                $("#eempType").val(empTypeNo);
            } else if (empTypeNo <= 6) {
                $("#e2").click();
                $("#eempType").val(empTypeNo);
            } else {
                $("#e3").click();
                $("#eempType").val(empTypeNo);
            }
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