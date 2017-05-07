package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.RestaurantOwner;

public class ManageMenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String target = "ToMenuServlet";
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String type = request.getParameter("submit");
        String[] stMenuNo = request.getParameterValues("table_records");
        if (stMenuNo != null) {
            if (type.equalsIgnoreCase("del")) {
                Menu.delAllMenu(stMenuNo, ro.getBranchNo());
            } else if (type.equalsIgnoreCase("open")) {
                Menu.manageAvailableAllMenu(stMenuNo, ro.getBranchNo(), 1);
            } else if (type.equalsIgnoreCase("close")) {
                Menu.manageAvailableAllMenu(stMenuNo, ro.getBranchNo(), 0);
            } else if (type.equalsIgnoreCase("addtobranch")) {
                Menu.addAllMenuIntoBranch(stMenuNo, ro.getBranchNo());
            }
        }
        response.sendRedirect(target);
        return;
    }
}
