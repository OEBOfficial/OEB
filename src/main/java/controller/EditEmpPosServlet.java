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
import model.EmployeePosition;

/**
 *
 * @author USER
 */
public class EditEmpPosServlet extends HttpServlet {

    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToEmpPosServlet";
        String positionName = request.getParameter("positionname");
        int positionNo = Integer.parseInt(request.getParameter("positionno"));
        HttpSession hs = request.getSession();
        EmployeePosition ep = new EmployeePosition();
        ep.setPositionNo(positionNo);
        ep.setPositionName(positionName);
        ep.editEmpPosName();
        response.sendRedirect(target);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
