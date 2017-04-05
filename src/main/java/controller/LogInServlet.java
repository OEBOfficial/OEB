package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.RestaurantOwner;
import model.ActorManagement;

public class LogInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String target = "/login.jsp";
        HttpSession hs = request.getSession(false);
        Cookie cookies[] = request.getCookies();
        String username = null;
        RestaurantOwner ro = null;
        boolean pass = false;

        if (cookies != null) {
            username = ActorManagement.chkCookie(cookies);
            if (username != null) {
                ro = RestaurantOwner.signInForCookie(username);
                hs = request.getSession();
                hs.setAttribute("restowner", ro);
                pass = true;
            }
        }else if (hs != null) {
            ro = (RestaurantOwner) hs.getAttribute("restowner");
            if (ro != null) {
                pass = true;
            }
        }
        
        if (pass) {
//          set time in
            hs.setAttribute("time-in", System.currentTimeMillis());
            response.sendRedirect("ToEmpServlet");
            return;
        } else {
            getServletContext().getRequestDispatcher(target).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = "/login.jsp";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");

        RestaurantOwner ro = RestaurantOwner.logIn(username, password);
        if (ro == null) {
            request.setAttribute("msg", "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง");
            request.setAttribute("username",username);
            if (rememberme != null) {
                request.setAttribute("rememberme", "checked");
            }
            getServletContext().getRequestDispatcher(target).forward(request, response);
        } else {
            HttpSession hs = request.getSession(true);
            hs.setAttribute("restowner", ro);
            hs.setAttribute("time-in", System.currentTimeMillis());
            if (rememberme != null) {
                Cookie c = new Cookie("restowner", ro.getRestUserName());
                c.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(c);
            }
            response.sendRedirect("ToEmpServlet");
            return;
        }
    }
}
