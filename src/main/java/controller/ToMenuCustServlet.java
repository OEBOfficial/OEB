package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuConfigLevel;
import model.RestaurantOwner;

public class ToMenuCustServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/menucust.jsp";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        List<MenuConfigLevel> mc = MenuConfigLevel.getAllMenuConfig();
        request.setAttribute("menucust", mc);
        request.setAttribute("target","menucust");
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
