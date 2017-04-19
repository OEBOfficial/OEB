/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Constraint;
import model.Employee;
import model.RestaurantOwner;

/**
 *
 * @author USER
 */
public class EditEmpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String target = "ToEmpServlet";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        Employee e = new Employee();
        String empName = request.getParameter("empName");
        String gender = request.getParameter("gender");
        String telNo = request.getParameter("telNo");
        int positionNo = Integer.parseInt(request.getParameter("empPos"));
        int empNo = Integer.parseInt(request.getParameter("empNo"));
        // Special Pay
        if(Integer.parseInt(request.getParameter("c-specPay")) == 2){
            double specPay = Double.parseDouble(request.getParameter("specPay"));
            e.setSpecPay(specPay);
        }
        int empTypeNo = Integer.parseInt(request.getParameter("etype"));
        int payTypeNo = Integer.parseInt(request.getParameter("empType"));
        int constraintNo = Constraint.findConstraintNo(positionNo,empTypeNo,payTypeNo);
        
        e.setBranchNo(ro.getBranchNo());
        Constraint c = new Constraint();
        c.setConstraintNo(constraintNo);
        e.setConstraint(c);
        e.setEmpName(empName);
        e.setEmpNo(empNo);
        e.setGender(gender);
        e.setTelNo(telNo);
        e.editEmp();
        response.sendRedirect(target);
        return;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>

}
