package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuType;

public class EditMenuTypeServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToMenuTypeServlet";
        int menuTypeNo = Integer.parseInt(request.getParameter("menuTypeNo"));
        String menuTypeName = request.getParameter("menuTypeName");
        MenuType.editMenuType(menuTypeNo,menuTypeName);
        response.sendRedirect(target);
        return;
    }
}
