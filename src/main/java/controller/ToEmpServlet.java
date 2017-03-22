/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Branch;
import model.Employee;
import model.EmployeePosition;
import model.EmploymentType;
import model.RestaurantOwner;

/**
 *
 * @author USER
 */
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
        request.setAttribute("employees", employees);
        List<EmploymentType> empTypes = EmploymentType.getAllEmpType();
        request.setAttribute("empTypes", empTypes);
        List<EmployeePosition> empPos = EmployeePosition.getAllEmpPos(ro.getBranchNo());
        request.setAttribute("empPos", empPos);
        request.setAttribute("target","empdata");
        
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
