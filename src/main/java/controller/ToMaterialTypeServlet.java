package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MaterialType;

public class ToMaterialTypeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/materialtype.jsp";
        List<MaterialType> matTypes = MaterialType.getAllMaterialType();
        request.setAttribute("matTypes", matTypes);
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
