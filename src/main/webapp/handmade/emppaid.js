$( document ).ready(function() {
  var withdrawtable = $("#withdrawtable").DataTable();
});

function setemppaid(empNo) {
    $.ajax({
        type: "GET",
        url: "SetEmpPaidServlet",
        dataType: "json",
        data: "empNo=" + encodeURIComponent(empNo),
        success: function (json) {
            $("#workhisttable").children().remove();
            $("#empName").html(json[0].empName);
            sumpay = 0;
            var historytable = $("#datatable-checkbox").DataTable();
            var withdrawtable = $("#withdrawtable").DataTable();
            historytable.clear().draw();
            withdrawtable.clear().draw();
            var varStatus = 1;
            for (var i = 0; i < json.length; i++) {
                hour = Math.floor((json[i].toTime - json[i].fromTime) / 60);
                min = ((json[i].toTime - json[i].fromTime) % 60);
                lowerThanConHr = hour + (min / 60) < json[i].minHoursPerDay;
                withdraw = (json[i].empTypeName === 'withdraw');
                if (withdraw) {
                    pasthour = Math.round(json[i].fromTime/60);
                    pasttime = Math.round(json[i].fromTime%60);
                    withdrawtable.row.add({
                       0: varStatus++,
                       1: json[i].fromDate + " " + (pasthour < 10?"0"+pasthour:pasthour) + "." + (pasttime < 10?"0"+pasttime:pasttime) + " น.",
                       2: Math.abs(json[i].workingPay).toFixed(2) + " ฿"
                    });
                } else {
                    historytable.row.add({
                        0: json[i].fromDate,
                        1: hour + " ชั่วโมง " + min + " นาที",
                        2: json[i].positionName,
                        3: json[i].empTypeName,
                        4: Number(json[i].workingPay).toFixed(2) + " ฿"
                    });
                }
                withdrawtable.draw();
                historytable.draw();
                sumpay += json[i].workingPay;
                $("#sumpay").html(sumpay.toFixed(2));
                $("#empno").val(json[i].empNo);
            }
        }
    });
}

$("#css-irow").click(function () {
    inputwd = $("#input-withdraw").val();
    empno = $("#empno").val();
    if (inputwd === '') {
        alert('กรุณาใส่เงินที่ต้องการเบิกก่อน');
    } else if (inputwd <= sumpay && inputwd > 0) {
        swal({
            title: "คุณต้องการเบิกเงินให้ " + $("#empName").text() + " ?",
            text: "จำนวนเงิน " + inputwd + " ฿ จากที่เบิกได้ " + sumpay + " ฿",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            cancelButtonText: "ยกเลิก",
            confirmButtonText: "ใช่, ฉันต้องการเบิก",
            closeOnConfirm: false
        },
                function () {
                    $.ajax({
                        type: "POST",
                        url: "PayEmpAjaxServlet",
                        dataType: "text",
                        data: "inputwd=" + encodeURIComponent(inputwd) + " &empno=" + encodeURIComponent(empno),
                        success: function (result) {
                            swal("เรียบร้อย", "เบิกเงินพนักงานเรียบร้อยแล้ว", "success");
                            setTimeout(function () {
                                location.reload();
                            }, 2000);
                        }
                    });
                });
    } else {
        alert('เงินที่ต้องการเบิกน้อยกว่าเงินที่เบิกได้ หรือใส่เงินที่ต้องการเบิกเป็น 0 หรือน้อยกว่า');
    }
});