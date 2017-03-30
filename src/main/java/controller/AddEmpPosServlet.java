package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Constraint;
import model.EmployeePosition;
import model.RestaurantOwner;

public class AddEmpPosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String target = "ToEmpPosServlet";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String positionName = request.getParameter("empPosName");
        EmployeePosition ep = new EmployeePosition();
        ep.setPositionName(positionName);
        ep.setBranchNo(ro.getBranchNo());
        Integer empPosNo = ep.addEmpPosName();
        System.out.println(empPosNo);
        if (empPosNo != null) {
            String[] empTypes = request.getParameterValues("emptype");
            String[] payTypes = request.getParameterValues("paytype");
            String[] maxHour = request.getParameterValues("maxhour");
            String[] minHour = request.getParameterValues("minhour");
            String[] pay = request.getParameterValues("pay");
            String proportion = request.getParameter("proportion");
            if (empTypes != null) {
                boolean success = Constraint.addConstraints(empPosNo,empTypes,payTypes,maxHour,minHour,pay,proportion);
            }
        }
        //จะรู้ได้ยังไง ว่า empposno อะไร

        response.sendRedirect(target);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
