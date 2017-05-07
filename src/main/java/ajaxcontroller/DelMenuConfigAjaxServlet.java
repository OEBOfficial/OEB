//check code I
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuConfigLevel;

public class DelMenuConfigAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int menuConfigNo = Integer.parseInt(request.getParameter("menuConfigNo"));
        boolean success = MenuConfigLevel.delMenuConfig(menuConfigNo);
        PrintWriter out = response.getWriter();
        out.print(success);
    }
}
