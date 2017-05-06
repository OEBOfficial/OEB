package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmployeePosition;
import model.RestaurantOwner;

public class ToEmpPosServlet extends HttpServlet {
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/emppos.jsp";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        
        List<EmployeePosition> emppos = EmployeePosition.getAllEmpPos(ro.getBranchNo());
        
        request.setAttribute("emppos", emppos);
        request.setAttribute("target", "emppos");
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
