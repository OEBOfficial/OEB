package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuType;
import model.RestaurantOwner;

public class AddMenuTypeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMenuTypeServlet";
        String menuType = request.getParameter("menuType");
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        MenuType m = new MenuType();
        m.setMenuTypeName(menuType);
        m.addMenuType(ro.getBranchNo());
        response.sendRedirect(target);
        return ;
    }
}
