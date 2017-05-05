//check code I
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Menu;

public class SetMenuAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int menuNo = Integer.parseInt(request.getParameter("menuNo"));
        PrintWriter out = response.getWriter();
        JsonObject menuJO = Menu.getMenuJson(menuNo);
        out.print(menuJO);
        out.flush();
        out.close();
    }
}
