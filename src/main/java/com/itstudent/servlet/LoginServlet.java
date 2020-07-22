package com.itstudent.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Cookie[] cookies = req.getCookies();
        for (Cookie c: cookies) {
           String name = c.getName();
           if (name.equals("username")){
               String value = c.getValue();
               if (value.equals("admin")){
                   req.setAttribute("username", value);
                   req.getRequestDispatcher("/user").forward(req, resp);
               }
           }
        }

        String username = req.getParameter("username");
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);
    }
}
