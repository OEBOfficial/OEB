package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Employee;
import model.RestaurantOwner;

public class ManageEmpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession();
        String target = "ToEmpServlet";
        RestaurantOwner ro = (RestaurantOwner) hs.getAttribute("restowner");
        String[] stEmpNo = request.getParameterValues("table_records");
        Employee.delAllEmp(stEmpNo);
        response.sendRedirect(target);
        return;
    }
}
