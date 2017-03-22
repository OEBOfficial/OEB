package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.WorkingHistory;

public class SetEmpPaidServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int empNo = Integer.parseInt(request.getParameter("empNo"));
        JsonArray workJO = WorkingHistory.getAllWorkHistByEmpJson(empNo);
        out.print(workJO);
        out.flush();
        out.close();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
