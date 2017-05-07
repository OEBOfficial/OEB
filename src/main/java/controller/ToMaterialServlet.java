package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Material;
import model.MaterialType;

public class ToMaterialServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/WEB-INF/material.jsp";
        List<Material> materials = Material.getAllMaterial();
        Map<Integer,MaterialType> matTypes = MaterialType.getAllMaterialTypeMap();
        request.setAttribute("materials",materials);
        request.setAttribute("matTypes", matTypes);
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
