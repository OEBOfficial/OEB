package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MaterialType;

public class AddMaterialTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMaterialTypeServlet";
        String materialTypeName = request.getParameter("materialType");
        MaterialType mt = new MaterialType();
        mt.setMatTypeName(materialTypeName);
        mt.addMaterialType();
        
        response.sendRedirect(target);
        return;
    }
}
