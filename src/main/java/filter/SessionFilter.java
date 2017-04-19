package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ActorManagement;
import model.RestaurantOwner;

public class SessionFilter implements Filter {

    private FilterConfig filterConfig;
    private final static String attributeName = "restowner";
    private final static String target = "/login.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //initial value and set encoding
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding("UTF-8");

        //get session and cookies
        HttpSession hs = request.getSession(false);
        Cookie cookies[] = request.getCookies();

        boolean pass = false;
        //check session then cookie
        pass = chkSession(hs);
        if (!pass) {
            pass = chkCookie(cookies, hs, request);
        }

        if (pass) {
            chain.doFilter(request, response);
        } else {
            filterConfig.getServletContext().getRequestDispatcher(target).forward(request, response);
        }

    }

    private boolean chkSession(HttpSession hs) throws IOException, ServletException {
        if (hs != null) {
            Object ro = hs.getAttribute(attributeName);
            if (ro != null) {
                return true;
            }
        }
        return false;
    }

    private boolean chkCookie(Cookie[] cookies, HttpSession hs, HttpServletRequest request) throws IOException, ServletException {
        if (cookies != null) {
            String username = ActorManagement.chkCookie(cookies);
            if (username != null) {
                RestaurantOwner ro = RestaurantOwner.signInForCookie(username);
                hs = request.getSession();
                hs.setAttribute("restowner", ro);
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        // destroy here
    }
}
