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
import model.EmployeePosition;
import model.RestaurantOwner;

/**
 *
 * @author USER
 */
public class ToEmpPosServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/emppos.jsp";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        int branchNo = ro.getBranchNo();
        List<EmployeePosition> emppos = EmployeePosition.getAllEmpPos(branchNo);
        
        request.setAttribute("emppos", emppos);
        request.setAttribute("target", "emppos");
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
