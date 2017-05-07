package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuConfigLevel;
import model.RestaurantOwner;

public class ManageMenuCustServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String target = "ToMenuCustServlet";
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String[] stMenuCustNo = request.getParameterValues("table_records");
        
        MenuConfigLevel.delAllMenuConfig(stMenuCustNo);
        response.sendRedirect(target);
        return;
    }
}
