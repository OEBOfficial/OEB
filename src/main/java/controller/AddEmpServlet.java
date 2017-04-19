package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Constraint;
import model.Employee;
import model.RestaurantOwner;

public class AddEmpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String target = "ToEmpServlet";
        HttpSession hs = request.getSession();
        RestaurantOwner ro = (RestaurantOwner)hs.getAttribute("restowner");
        int branchNo = ro.getBranchNo();
        Employee e = new Employee();
        String empName = request.getParameter("empName");
        String gender = request.getParameter("gender");
        String telNo = request.getParameter("telNo");
        if(request.getParameter("specPay") != null){
            double specPay = Double.parseDouble(request.getParameter("specPay"));
            e.setSpecPay(specPay);
        }
        int empTypeNo = Integer.parseInt(request.getParameter("etype"));
        int payTypeNo = Integer.parseInt(request.getParameter("payType"));
        int positionNo = Integer.parseInt(request.getParameter("empPos"));
        int constraintNo = Constraint.findConstraintNo(positionNo,empTypeNo,payTypeNo);
        
        e.setBranchNo(branchNo);
        e.setEmpName(empName);
        e.setGender(gender);
        e.setTelNo(telNo);
        Constraint c = new Constraint();
        c.setConstraintNo(constraintNo);
        e.setConstraint(c);
        
        e.addEmp();
        response.sendRedirect(target);
    }
}
