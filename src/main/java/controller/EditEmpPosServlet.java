package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Constraint;
import model.RestaurantOwner;

public class EditEmpPosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String target = "ToEmpPosServlet";
//        HttpSession hs = request.getSession();
//        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
//        Integer empPosNo = Integer.parseInt(request.getParameter("empPosNo"));
//        String[] empTypes = request.getParameterValues("emptype");
//        String[] payTypes = request.getParameterValues("paytype");
//        String[] maxHour = request.getParameterValues("maxhour");
//        String[] minHour = request.getParameterValues("minhour");
//        String[] pay = request.getParameterValues("pay");
//        String proportion = request.getParameter("proportion");
//        if (empTypes != null) {
//            boolean success = Constraint.editConstraints(empPosNo, empTypes, payTypes, maxHour, minHour, pay, proportion, ro.getBranchNo());
//        }
//        response.sendRedirect(target);
    }
}
