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
public class AddConstraintServlet extends HttpServlet {

    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "ToConstraintAjaxServlet";
        int empTypeNo = Integer.parseInt(request.getParameter("emptypeno"));
        int positionNo = Integer.parseInt(request.getParameter("positionno"));
        double hoursPerDay = Double.parseDouble(request.getParameter("hoursperday"));
        double pay = Double.parseDouble(request.getParameter("pay"));
        
        Constraint c = new Constraint();
        c.setEmpTypeNo(empTypeNo);
        c.setPositionNo(positionNo);
        c.setPay(pay);
        c.setHoursPerDay(hoursPerDay);
        c.addConstraint();
        response.sendRedirect(target);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
