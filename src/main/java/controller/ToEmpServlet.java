package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Constraint;
import model.Employee;
import model.EmployeePosition;
import model.RestaurantOwner;

public class ToEmpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String target = "/WEB-INF/empindex.jsp";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        List<Employee> employees = Employee.getAllEmp(ro.getBranchNo());
        List<EmployeePosition> empPos = EmployeePosition.getAllEmpPos(ro.getBranchNo());
        Map<String,String> constraintMap = Constraint.getMapConstraint(ro.getBranchNo());
        request.setAttribute("employees", employees);
        request.setAttribute("empPos", empPos);
        request.setAttribute("target","empdata");
        request.setAttribute("constraintMap",constraintMap);
        
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
