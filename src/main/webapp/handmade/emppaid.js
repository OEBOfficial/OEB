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
            for (var i = 0; i < json.length; i++) {
                hour = Math.floor((json[i].toTime - json[i].fromTime) / 60);
                min = ((json[i].toTime - json[i].fromTime) % 60);
                lowerThanConHr = hour + (min / 60) < json[i].minHoursPerDay;
                withdraw = (json[i].empTypeName === 'withdraw');
                if(withdraw){
                    pasthour = Math.round(json[i].fromTime/60);
                    pasttime = Math.round(json[i].fromTime%60);
                    $("#workhisttable").append("<tr class= 'body-rows-receive' style='text-align:center;'>\n\
                                                <td colspan='5'> วันที่ " + json[i].fromDate + " เวลา " + 
                                                (pasthour < 10?"0"+pasthour:pasthour) + "." + (pasttime < 10?"0"+pasttime:pasttime) + 
                                                " น. ทำการเบิกเงินไป " + Math.abs(json[i].workingPay).toFixed(2) + " ฿ </td>" +                                              
                                                "</tr>");
                }else{
                    $("#workhisttable").append("<tr class = '" + (lowerThanConHr ? 'body-rows-lesshrs' : 'body-rows') + "'>\n\
                                                    <td>" + json[i].fromDate + "</td>\n\
                                                    <td>" + hour + " ชั่วโมง " + min + " นาที</td>\n\
                                                    <td>" + json[i].positionName + "</td>\n\
                                                    <td>" + json[i].empTypeName + " ราย" + json[i].payTypeName + "</td>\n\
                                                    <td>" + Number(json[i].workingPay).toFixed(2) + " ฿</td>\n\
                                                </tr>");
                }
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
    } else if(inputwd <= sumpay && inputwd > 0){
        swal({
            title: "คุณต้องการเบิกเงินให้ "+$("#empName").text()+" ?",
            text: "จำนวนเงิน " + inputwd + " ฿ จากที่เบิกได้ " + sumpay +" ฿",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            cancelButtonText: "ยกเลิก",
            confirmButtonText: "ใช่, ฉันต้องการเบิก",
            closeOnConfirm: false
        },
        function(){
            $.ajax({
                type: "POST",
                url: "PayEmpAjaxServlet",
                dataType: "text",
                data: "inputwd=" + encodeURIComponent(inputwd) + " &empno=" + encodeURIComponent(empno),
                success: function (result) {
                    swal("เรียบร้อย", "เบิกเงินพนักงานเรียบร้อยแล้ว", "success");
                    setTimeout(function(){
                        location.reload();
                    },2000);
                }
            });
        });
    }else{
        alert('เงินที่ต้องการเบิกน้อยกว่าเงินที่เบิกได้ หรือใส่เงินที่ต้องการเบิกเป็น 0 หรือน้อยกว่า');
    }
});