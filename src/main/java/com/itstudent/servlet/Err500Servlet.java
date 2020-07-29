package com.itstudent.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/500")
public class Err500Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer stt = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String uri = (String) req.getAttribute("javax.servlet.error.request_uri");
        resp.getWriter().println("<h1>Sorry! my mistake</h1>");
        resp.getWriter().println("<h3>"+ throwable.getMessage() +"</h3>");
        resp.getWriter().println("<h3>"+ stt +"</h3>");
        resp.getWriter().println("<h3>"+ uri +"</h3>");
    }
}
