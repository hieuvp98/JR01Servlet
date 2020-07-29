package com.itstudent.filter;

import com.itstudent.util.EncodeUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

@WebFilter("/api/private/*")
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String token = req.getHeader("Authorization");
        if (token != null){
            String username = EncodeUtil.decode(token);
            if (username != null){
                req.setAttribute("userPrinciple", username);
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                resp.setStatus(401);
                resp.getWriter().println("Unauthenticated");
            }
        } else{
            resp.setStatus(401);
            resp.getWriter().println("Unauthenticated");
        }
    }

    @Override
    public void destroy() {

    }
}
