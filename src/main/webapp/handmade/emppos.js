$(document).ready(function () {
    $("#emptab").parent().addClass('active');
    $("#emptab").parent().find('ul').css('display', 'block');
    $("#emppos").parent().addClass('active');
});

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