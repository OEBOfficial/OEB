//check code I
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.RestaurantOwner;
import model.WorkingHistory;

public class PayEmpAjaxServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double inputwd = Double.parseDouble(request.getParameter("inputwd"));
        int empNo = Integer.parseInt(request.getParameter("empno"));
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        boolean success = WorkingHistory.payEmp(empNo,inputwd,ro.getBranchNo());
        PrintWriter out = response.getWriter();
        out.print(success);
    }
}
