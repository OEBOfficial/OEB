package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.MenuSet;
import model.RestaurantOwner;

public class AddMenuToBranchServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        int menuNo = Integer.parseInt(request.getParameter("menuNo"));
        int isAvailable = 0;
        if(request.getParameter("isAvailable") != null){
            isAvailable = 1;
        }
        Menu.addMenuIntoBranch(menuNo, ro.getBranchNo(), isAvailable);
        response.sendRedirect("ToMenuServlet");
        return;
    }
}