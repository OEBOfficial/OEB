function delMaterial(matNo) {
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
                    type: "Post",
                    url: "DelMaterialAjaxServlet",
                    dataType: "text",
                    data: "matNo=" + encodeURIComponent(matNo),
                    success: function (result) {
                        swal("เรียบร้อย", "ข้อมูลวัตถุดิบถูกลบแล้ว", "success");
                        $("#datatable-checkbox").DataTable().row("#tr" + matNo).remove().draw();
                    }
                });
            });
}

function setMaterial(materialNo) {
    $(".editMaterialName").html($("#materialNo" + materialNo).text());
    $(".editMaterialName").val($("#materialNo" + materialNo).text());
    $("#editMatTypeNo").children().each(function(){
        if($(this).text() === $("#matType" + materialNo).text()){
            $(this).attr('selected','true');
        }
    });
    $("#editMaterialNo").val(materialNo);
}