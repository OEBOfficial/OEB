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
import model.WorkingHistory;

/**
 *
 * @author USER
 */
public class FilterWorkByDateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/emphistory.jsp";
        String dateRange = request.getParameter("date");
        String fromDate = dateRange.substring(6,10) + "-" + dateRange.substring(3,5) + "-" + dateRange.substring(0,2);
        String toDate = dateRange.substring(19,23) + "-" + dateRange.substring(16,18) + "-" + dateRange.substring(13,15);
        List<WorkingHistory> workhist = WorkingHistory.filterWorkByDate(fromDate, toDate);
        request.setAttribute("target", "workhist");
        request.setAttribute("workhist", workhist);
        request.setAttribute("daterange", dateRange);
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
