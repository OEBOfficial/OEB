//check code I
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuType;

public class DelMenuTypeAjaxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int menuTypeNo = Integer.parseInt(request.getParameter("menuTypeNo"));
        boolean success = MenuType.delMenuType(menuTypeNo);
        PrintWriter out = response.getWriter();
        out.print(success);
    }
}
