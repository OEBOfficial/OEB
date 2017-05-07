function delMaterialType(matTypeNo) {
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
                    url: "DelMaterialTypeAjaxServlet",
                    dataType: "text",
                    data: "matTypeNo=" + encodeURIComponent(matTypeNo),
                    success: function (result) {
                        swal("เรียบร้อย", "ข้อมูลประเภทวัตถุดิบถูกลบแล้ว", "success");
                        $("#datatable-checkbox").DataTable().row("#tr" + matTypeNo).remove().draw();
                    }
                });
            });
}

function setMaterialTypeName(materialTypeNo) {
    $(".editMaterialTypeName").html($("#materialTypeNo" + materialTypeNo).text());
    $(".editMaterialTypeName").val($("#materialTypeNo" + materialTypeNo).text());
    $("#editMaterialTypeNo").val(materialTypeNo);
}