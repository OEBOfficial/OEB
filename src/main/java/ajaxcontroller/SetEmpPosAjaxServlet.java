/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Constraint;
import model.EmployeePosition;

/**
 *
 * @author USER
 */
public class SetEmpPosAjaxServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int empPosNo = Integer.parseInt(request.getParameter("positionNo"));
        PrintWriter out = response.getWriter();
        EmployeePosition ep = EmployeePosition.getEmpPos(empPosNo);
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        JsonObject mainJO = EmployeePosition.getEmpPosJson(empPosNo);
        JsonArray constraintJA = Constraint.getAllConJson(empPosNo);
        mainJO = JOB.add("empPos", mainJO).add("constraints", constraintJA).build();
        
        out.print(mainJO);
        out.flush();
        out.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
