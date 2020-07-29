package com.itstudent.servlet;

import com.google.gson.Gson;
import com.itstudent.dao.UserDao;
import com.itstudent.model.AppUser;
import com.itstudent.model.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/login"})
public class LoginServlet extends HttpServlet {

    private UserDao dao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        LoginForm loginForm = gson.fromJson(req.getReader(), LoginForm.class);
        AppUser appUser = dao.checkLogin(loginForm.getUsername(), loginForm.getPassword());
        if (appUser != null) {
            Cookie cookie = new Cookie("username", appUser.getUsername());
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);
            resp.getWriter().println("login success");
        } else {
            resp.setStatus(400);
            resp.getWriter().println("login fail");
        }

    }
}
