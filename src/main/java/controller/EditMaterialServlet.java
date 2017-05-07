package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Material;

public class EditMaterialServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMaterialServlet";
        int matNo = Integer.parseInt(request.getParameter("matNo"));
        int matTypeNo = Integer.parseInt(request.getParameter("matTypeNo"));
        String matName = request.getParameter("matName");
        Material m = new Material();
        m.setMatName(matName);
        m.setMatNo(matNo);
        m.setMatTypeNo(matTypeNo);
        m.editMaterial();
        response.sendRedirect(target);
        return;
    }
}
