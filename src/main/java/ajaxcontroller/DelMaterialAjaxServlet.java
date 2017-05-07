package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Material;

public class DelMaterialAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int matNo = Integer.parseInt(request.getParameter("matNo"));
        boolean success = Material.delMaterial(matNo);
        PrintWriter out = response.getWriter();
        out.print(success);
    }
}
