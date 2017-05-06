package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EmployeePosition;
import model.MaterialType;

public class DelMaterialTypeAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int matTypeNo = Integer.parseInt(request.getParameter("matTypeNo"));
        boolean success = MaterialType.delMaterialType(matTypeNo);
        PrintWriter out = response.getWriter();
        out.print(success);
    }
}
