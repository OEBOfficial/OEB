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
import model.Constraint;

/**
 *
 * @author USER
 */
public class EditConstraintServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToConstraintAjaxServlet";
        int empTypeNo = Integer.parseInt(request.getParameter("emptypeno"));
        int positionNo = Integer.parseInt(request.getParameter("positionno"));
        double minHoursPerDay = Double.parseDouble(request.getParameter("minhoursperday"));
        double maxHoursPerDay = Double.parseDouble(request.getParameter("maxhoursperday"));
        double pay = Double.parseDouble(request.getParameter("pay"));
        
        Constraint c = new Constraint();
        c.setEmpTypeNo(empTypeNo);
        c.setPositionNo(positionNo);
        c.setPay(pay);
        c.setMaxHoursPerDay(maxHoursPerDay);
        c.setMinHoursPerDay(minHoursPerDay);
        c.editConstraint();
        response.sendRedirect(target);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
