package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuSet;
import model.RestaurantOwner;

public class ManageMenuSetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String target = "ToMenuSetServlet";
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String type = request.getParameter("submit");
        String[] stMenuSetNo = request.getParameterValues("table_records");
        if (stMenuSetNo != null) {
            if (type.equalsIgnoreCase("del")) {
                MenuSet.delAllMenuSet(stMenuSetNo, ro.getBranchNo());
            } else if (type.equalsIgnoreCase("open")) {
                MenuSet.manageAvailableAllMenuSet(stMenuSetNo, ro.getBranchNo(), 1);
            } else if (type.equalsIgnoreCase("close")) {
                MenuSet.manageAvailableAllMenuSet(stMenuSetNo, ro.getBranchNo(), 0);
            } else if (type.equalsIgnoreCase("addtobranch")) {
                MenuSet.addAllMenuSetToBranch(stMenuSetNo, ro.getBranchNo());
            }
        }
        response.sendRedirect(target);
        return;
    }
}
