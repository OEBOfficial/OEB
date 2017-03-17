/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Branch;
import model.Employee;

/**
 *
 * @author USER
 */
public class AddEmpServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToEmpServlet";
        HttpSession hs = request.getSession();
        String empName = request.getParameter("empName");
        String idCardNo = request.getParameter("idCardNo");
        String gender = request.getParameter("gender");
        String telNo = request.getParameter("telNo");
        double specPay = Double.parseDouble(request.getParameter("specPay"));
        int empTypeNo = Integer.parseInt(request.getParameter("empTypeNo"));
        int positionNo = Integer.parseInt(request.getParameter("positionNo"));
        Branch b = (Branch)hs.getAttribute("branch");
        int branchNo = b.getBranchNo();
        Employee e = new Employee();
        e.setBranchNo(branchNo);
        e.setEmpName(empName);
        e.setEmpNo(empTypeNo);
        e.setEmpPosNo(empTypeNo);
        e.setEmpTypeNo(empTypeNo);
        e.setGender(gender);
        e.setIdCardNo(idCardNo);
        e.setSpecPay(specPay);
        e.setTelNo(telNo);
        e.addEmp();
        response.sendRedirect(target);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
