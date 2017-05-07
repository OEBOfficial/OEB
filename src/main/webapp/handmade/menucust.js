function delMenuConfig(menuConfigNo) {
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
                    type: "POST",
                    url: "DelMenuConfigAjaxServlet",
                    dataType: "text",
                    data: "menuConfigNo=" + encodeURIComponent(menuConfigNo),
                    success: function (result) {
//                        swal("เรียบร้อย", "เมนูอาหารถูกลบออกเรียบร้อยแล้ว", "success");
                        location.reload();
                    }
                });
            });
}