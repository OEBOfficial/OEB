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
import model.EmployeePosition;
import model.RestaurantOwner;

/**
 *
 * @author USER
 */
public class AddEmpPosServlet extends HttpServlet {

    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToEmpPosServlet";
        String positionName = request.getParameter("positionname");
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
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
