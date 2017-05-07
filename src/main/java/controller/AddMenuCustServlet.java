package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuConfigLevel;

public class AddMenuCustServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMenuCustServlet";
        String menuCustName = request.getParameter("menuCustName");
        String[] checkLevel = request.getParameterValues("checklevel");
        List<String> checkLevelName = new LinkedList<String>();
        if (checkLevel != null) {
            for (String cl : checkLevel) {
                checkLevelName.add(request.getParameter("menuConfig" + cl));
            }
        }
        MenuConfigLevel.addMenuConfig(menuCustName, checkLevelName);
        response.sendRedirect(target);
        return;
    }
}
