package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.MenuSet;
import model.RestaurantOwner;

public class ToMenuSetServlet extends HttpServlet {
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/menuset.jsp";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        List<MenuSet> menuSets = MenuSet.getAllMenuSet(ro.getBranchNo());
        List<Menu> menus = Menu.getAllMenu(ro.getBranchNo());
        request.setAttribute("menusets",menuSets);
        request.setAttribute("menus",menus);
        request.setAttribute("target","menuset");
        request.getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
