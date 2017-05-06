package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MaterialType;

public class EditMaterialTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMaterialTypeServlet";
        int matTypeNo = Integer.parseInt(request.getParameter("matTypeNo"));
        String matTypeName = request.getParameter("materialTypeName");
        MaterialType mt = new MaterialType();
        mt.setMatTypeName(matTypeName);
        mt.setMatTypeNo(matTypeNo);
        mt.editMaterialType();
        response.sendRedirect(target);
        return;
    }
}
