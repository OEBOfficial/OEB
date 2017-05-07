package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MaterialType;
import model.RestaurantOwner;

public class ManageMaterialTypeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String target = "ToMaterialTypeServlet";
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String[] stMatTypeNo = request.getParameterValues("table_records");
        MaterialType.delAllMaterialType(stMatTypeNo);
        response.sendRedirect(target);
        return;
    }
}
