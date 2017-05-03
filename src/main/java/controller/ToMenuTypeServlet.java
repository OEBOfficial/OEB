package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.MenuType;
import model.RestaurantOwner;

public class ToMenuTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/menutype.jsp";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        List<MenuType> menuTypes = MenuType.getAllMenuType(ro.getBranchNo());
        request.setAttribute("menuTypes",menuTypes);
        request.setAttribute("target","menutype");
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
