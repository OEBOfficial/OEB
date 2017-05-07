package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Material;

public class AddMaterialServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMaterialServlet";
        int matTypeNo = Integer.parseInt(request.getParameter("matTypeNo"));
        String matName = request.getParameter("matName");
        Material m = new Material();
        m.setMatTypeNo(matTypeNo);
        m.setMatName(matName);
        m.addMaterial();
        
        response.sendRedirect(target);
        return;
    }
}
