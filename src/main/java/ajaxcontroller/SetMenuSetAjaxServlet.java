//check code I
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.MenuSet;
import model.RestaurantOwner;

public class SetMenuSetAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession hs = request.getSession();
        int menuSetNo = Integer.parseInt(request.getParameter("menuSetNo"));
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        PrintWriter out = response.getWriter();
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        JsonObject JO = JOB.add("menuset", Menu.getMenuByMenuSetJson(menuSetNo , ro.getBranchNo()))
                           .add("menusetinfo", MenuSet.getMenuSetJson(menuSetNo)).build();
        out.print(JO);
        out.flush();
        out.close();
    }
}
