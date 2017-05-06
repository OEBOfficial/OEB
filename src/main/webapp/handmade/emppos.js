// for add emp pos modal
$(".emptype").click(function () {
    var paytype = $(".paytype" + $(this).val());
    if (paytype.attr('disabled') === 'disabled') {
        paytype.attr('disabled', false);
    } else {
        paytype.attr('disabled', true);
        paytype.attr('checked', false);
    }
    paytype.each(function () {
        $(this).parent().siblings(":last").find("input").attr('disabled', true);
        $(this).parent().siblings(":last").find("input").val('');
    });
});

$(".paytype").change(function () {
    var input = $(this).parent().siblings(":last").find("input");
    input.each(function () {
        if ($(this).attr('disabled') === 'disabled') {
            $(this).attr('disabled', false);
        } else {
            $(this).attr('disabled', true);
            $(this).val('');
        }
    });
});

$("#addemppos").submit(function () {
    var emptype = $(".emptype");
    var error = false;
    emptype.each(function () {
        if (this.checked) {
            var paytype = $(".paytype" + $(this).val());
            var count = 0;
            var serve = 0;
            paytype.each(function () {
                if (!this.checked) {
                    count++;
                } else {
                    serve++;
                }
            });
            if (count === paytype.size()) {
                error = true;
            }
            $("#hiddensub").val($("#hiddensub").val() + "" + serve);
        }
    });
    if (error) {
        alert('คุณไม่ได้เลือกประเภทการจ่ายเงินช่องใดช่องหนึ่ง ขณะที่คุณเลือกประเภทการทำงานค้างไว้');
        $("#hiddensub").val('');
        return false;
    } else {
        return true;
    }
});
//For Emp Pos Modal

// for editing Emp Pos Modal
$(".editemptype").click(function () {
    $(this).attr('checked', true);
    var paytype = $(".editpaytype" + $(this).val());
    if (paytype.attr('disabled') === 'disabled') {
        paytype.attr('disabled', false);
    } else {
        paytype.attr('disabled', true);
        paytype.attr('checked', false);
    }
    paytype.each(function () {
        $(this).parent().siblings(":last").find("input").attr('disabled', true);
        $(this).parent().siblings(":last").find("input").val('');
    });
});

$(".editpaytype").click(function () {
    var input = $(this).parent().siblings(":last").find("input");
    input.each(function () {
        if ($(this).attr('disabled') === 'disabled') {
            $(this).attr('disabled', false);
        } else {
            $(this).attr('disabled', true);
            $(this).val('');
        }
    });
});

$("#editform").submit(function () {
    var emptype = $(".editemptype");
    var error = false;
    emptype.each(function () {
        if (this.checked) {
            var paytype = $(".editpaytype" + $(this).val());
            var count = 0;
            var serve = 0;
            paytype.each(function () {
                if (!this.checked) {
                    count++;
                } else {
                    serve++;
                }
            });
            if (count === paytype.size()) {
                error = true;
            }
            $("#edithiddensub").val($("#edithiddensub").val() + "" + serve);
        }
    });
    if (error) {
        alert('คุณไม่ได้เลือกประเภทการจ่ายเงินช่องใดช่องหนึ่ง ขณะที่คุณเลือกประเภทการทำงานค้างไว้');
        $("#edithiddensub").val('');
        return false;
    } else {
        return true;
    }
});
//For Editing Emp Pos Modal



function delPosition(positionname, positionno) {
    swal({
        title: "คุณต้องการลบใช่หรือไม่ ?",
        text: "คุณจะไม่สามารถกู้ข้อมูล " + positionname + " กลับมาได้อีก!",
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
                    url: "DelEmpPosAjaxServlet",
                    dataType: "text",
                    data: "positionno=" + encodeURIComponent(positionno),
                    success: function (result) {
                        swal("เรียบร้อย", "ข้อมูลตำแหน่งพนักงานถูกลบแล้ว", "success");
                        $("#datatable").DataTable().row("#tr" + positionno).remove().draw();
                    }
                });
            });
}

function setConstraint(positionNo) {
    $.ajax({
        type: "POST",
        url: "SetEmpPosAjaxServlet",
        dataType: "json",
        data: "positionNo=" + encodeURIComponent(positionNo),
        success: function (json) {
            $("#editEmpPosName").html(json.empPos.positionName);
            $("#editEmpPosNo").val(json.empPos.positionNo);
            for (var i = 0; i < json.constraints.length; i++) {
                var c = json.constraints[i];
                var emptype = $(".editemptype[type='checkbox'][value=" + c.empTypeNo + "]");
                if (emptype.attr('checked') !== 'checked') {
                    emptype.click();
                }
                var paytype = $(".editpaytype" + c.empTypeNo + "[value=" + c.payTypeNo + "]");
                paytype.click();
                $("#minhour" + c.empTypeNo + c.payTypeNo).val(c.minHoursPerDay);
                $("#maxhour" + c.empTypeNo + c.payTypeNo).val(c.maxHoursPerDay);
                $("#pay" + c.empTypeNo + c.payTypeNo).val(c.pay);
            }
        }
    });
}

function editConstraint(empTypeNo, positionNo) {
    alert('testing');
}