package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WorkingHistory;

public class ClockOutAjaxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        int workNo = Integer.parseInt(request.getParameter("workNo"));
        int empNo = Integer.parseInt(request.getParameter("empNo"));
        WorkingHistory.clockOut(workNo,empNo);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
