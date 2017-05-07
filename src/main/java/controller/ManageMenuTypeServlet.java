package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuType;
import model.RestaurantOwner;

public class ManageMenuTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String target = "ToMenuTypeServlet";
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String[] stMenuTypeNo = request.getParameterValues("table_records");
        MenuType.delAllMenuType(stMenuTypeNo);
        response.sendRedirect(target);
        return;
    }
}
