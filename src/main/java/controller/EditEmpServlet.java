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
        String idCardNo = request.getParameter("idCardNo");
        String gender = request.getParameter("gender");
        int empNo = Integer.parseInt(request.getParameter("empNo"));
        // Special Pay
        if(Integer.parseInt(request.getParameter("c-specPay")) == 2){
            double specPay = Double.parseDouble(request.getParameter("specPay"));
            e.setSpecPay(specPay);
        }
        String telNo = request.getParameter("telNo");
        int empTypeNo = Integer.parseInt(request.getParameter("empType"));
        int positionNo = Integer.parseInt(request.getParameter("empPos"));
        e.setEmpNo(empNo);
        e.setEmpName(empName);
        e.setEmpTypeNo(empTypeNo);
        e.setPositionNo(positionNo);
        e.setGender(gender);
        e.setTelNo(telNo);
        e.setBranchNo(ro.getBranchNo());
        e.editEmp();
        response.sendRedirect(target);
        return;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>

}
