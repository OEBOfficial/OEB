package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EmployeePosition;


public class DelEmpPosAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int positionNo = Integer.parseInt(request.getParameter("positionno"));
        EmployeePosition.delEmpPos(positionNo);
    }

}
