package ua.com.bukvashops.pocupon.filters;

import ua.com.bukvashops.pocupon.Entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Igor on 2/16/2016.
 */
@WebFilter(filterName="AuthenticationFilter", urlPatterns="/*")
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        if((session == null
                || session.getAttribute("user") == null
                || session.getAttribute("user").equals(""))
                && !(uri.endsWith("login.jsp")
                || uri.endsWith("login"))){
            res.sendRedirect("/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {}
}
