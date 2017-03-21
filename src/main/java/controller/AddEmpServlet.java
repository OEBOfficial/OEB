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
public class AddEmpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String target = "ToEmpServlet";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        int branchNo = ro.getBranchNo();
        Employee e = new Employee();
        String empName = request.getParameter("empName");
        String idCardNo = request.getParameter("idCardNo");
        String gender = request.getParameter("gender");
        String telNo = request.getParameter("telNo");
        if(request.getParameter("specPay") != null){
            double specPay = Double.parseDouble(request.getParameter("specPay"));
            e.setSpecPay(specPay);
        }
        int empTypeNo = Integer.parseInt(request.getParameter("empType"));
        int positionNo = Integer.parseInt(request.getParameter("empPos"));
        e.setBranchNo(branchNo);
        e.setEmpName(empName);
        e.setPositionNo(positionNo);
        e.setEmpTypeNo(empTypeNo);
        e.setGender(gender);
        e.setTelNo(telNo);
        e.addEmp();
        response.sendRedirect(target);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
