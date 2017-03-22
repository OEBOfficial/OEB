function setemppaid(empNo) {
    $.ajax({
        type: "POST",
        url: "SetEmpPaidServlet",
        dataType: "json",
        data: "empNo=" + encodeURIComponent(empNo),
        success: function (json) {
            $("#workhisttable").children().remove();
            for(var i = 0; i<json.length;i++){
                $("#workhisttable").append("<tr><td>"+json[i].fromDate+"</td><td>"+json[i].empName+"</td><td>"+json[i].positionName+"</td><td>"+json[i].empTypeName+"</td><td>"+json[i].workingPay+"</td></tr>");
            }
        }
    });
}

$(document).ready(function () {
    $("#emptab").parent().addClass('active');
    $("#emptab").parent().find('ul').css('display', 'block');
    $("#emppaid").parent().addClass('active');
});