package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Employee;
import model.RestaurantOwner;


public class ToEmpPaidServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/emppaid.jsp";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        List<Employee> employees = Employee.getEmpByBranchWithSUMPAY(ro.getBranchNo()); // ผิดอยู่น้าาาาาาาาาาาาาา
        request.setAttribute("employees", employees);
        request.setAttribute("target", "emppaid");
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
