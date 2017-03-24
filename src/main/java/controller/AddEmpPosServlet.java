package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmployeePosition;
import model.RestaurantOwner;

public class AddEmpPosServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String target = "ToEmpPosServlet";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        String positionName = request.getParameter("positionName");
        EmployeePosition pe = new EmployeePosition();
        pe.setPositionName(positionName);
        pe.setBranchNo(ro.getBranchNo());
        pe.addEmpPosName();
        response.sendRedirect(target);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}