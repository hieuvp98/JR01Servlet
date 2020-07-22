package com.itstudent.filter;

import com.itstudent.dao.ConnectionController;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/api/*"})
public class ConnectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            if (ConnectionController.connection == null || ConnectionController.connection.isClosed()){
                System.out.println("Not connected");
                ConnectionController.connect();
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionController.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
