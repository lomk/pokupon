package ua.com.bukvashops.pocupon.filters;



import ua.com.bukvashops.pocupon.Entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Igor on 25-Mar-16.
 */
@WebFilter(filterName="AdminFilter", urlPatterns="/admin")
public class AdminFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        try {
            User user = (User) session.getAttribute("user");

            if (user.isAdmin() && uri.endsWith("admin")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("/check.jsp");
            }
        } catch (Exception e){
            res.sendRedirect("/check.jsp");
        }
    }

    public void destroy() {}
}
