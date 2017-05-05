//check code I
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MenuSet;
import model.RestaurantOwner;

public class DelMenuSetAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        int menuSetNo = Integer.parseInt(request.getParameter("menuSetNo"));
        boolean success = MenuSet.delMenuSet(menuSetNo,ro.getBranchNo());
        PrintWriter out = response.getWriter();
        out.print(success);
    }
}
