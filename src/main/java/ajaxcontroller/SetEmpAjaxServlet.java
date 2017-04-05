package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;

public class SetEmpAjaxServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int empNo = Integer.parseInt(request.getParameter("empno"));
        PrintWriter out = response.getWriter();
        JsonObject empJO = Employee.getEmployeeJson(empNo);
        out.print(empJO);
        out.flush();
        out.close();
    }
}
