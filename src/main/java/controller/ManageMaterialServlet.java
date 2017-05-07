package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Material;
import model.RestaurantOwner;

public class ManageMaterialServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String target = "ToMaterialServlet";
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String[] stMatNo = request.getParameterValues("table_records");
        Material.delAllMaterial(stMatNo);
        response.sendRedirect(target);
        return;
    }
}
