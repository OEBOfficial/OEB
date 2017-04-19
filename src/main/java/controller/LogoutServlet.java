package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/login.jsp";
        HttpSession hs = request.getSession(false);
        
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
        
        if (hs != null) {
            hs.invalidate();
        }
        
        getServletContext().getRequestDispatcher(target).forward(request, response);
    }
}
