$(document).ready(function () {
    $("#emptab").parent().addClass('active');
    $("#emptab").parent().find('ul').css('display', 'block');
    $("#emppos").parent().addClass('active');
});

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

$("form").submit(function () {
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
                }else{
                    serve++;
                }
            });
            if (count === paytype.size()) {
                error = true;
            }
            $("#hiddensub").val($("#hiddensub").val()+""+serve);
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

function delPosition(positionname,positionno) {
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

//function setConstraint(positionNo) {
//    $.ajax({
//        type: "POST",
//        url: "SetEmpPosAjaxServlet",
//        dataType: "json",
//        data: "positionNo=" + encodeURIComponent(positionNo),
//        success: function (json) {
//            $("#positionname").html(json.empPos.positionName);
//            $("#constrainttable").children().remove();
//            for(var i = 0 ; i < json.constraints.length ; i++){
//                var c = json.constraints[i];
//                if(c.empTypeNo === 1){
//                    var empTypeName = 'Full-Time';
//                }else if(c.empTypeNo === 2){
//                    var empTypeName = 'Part-Time';
//                }else if(c.empTypeNo === 3){
//                    var empTypeName = 'Training';
//                }
//                if(c.payType === 1){
//                    var payTypeName = 'ชั่วโมง';
//                }else if(c.payType === 2){
//                    var payTypeName = 'วัน';
//                }else if(c.payType === 3){
//                    var payTypeName = 'เดือน';
//                }
//                $("#constrainttable").append("<tr>");
//                $("#constrainttable").append("<td>"+empTypeName+"</td>");
//                $("#constrainttable").append("<td>"+c.minHoursPerDay+" ชั่วโมง</td>");
//                $("#constrainttable").append("<td>"+c.maxHoursPerDay+" ชั่วโมง</td>");
//                $("#constrainttable").append("<td>"+c.pay+" ต่อ"+payTypeName+"</td>");
//                $("#constrainttable").append("<td><a href='javascript:void(0)' onclick='editConstraint("+c.empTypeNo+","+c.positionNo+")'>แก้ไข</a></td>");
//                $("#constrainttable").append("</tr>");
//            }
//        }
//    });
//}
//
//function editConstraint(empTypeNo,positionNo){
//    alert('testing');
//}