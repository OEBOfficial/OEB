/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WorkingHistory;

/**
 *
 * @author USER
 */
public class ClockInAjaxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        int empNo = Integer.parseInt(request.getParameter("empNo"));
        WorkingHistory.clockIn(empNo);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
