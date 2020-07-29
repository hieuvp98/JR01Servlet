package com.itstudent.servlet;

import com.google.gson.Gson;
import com.itstudent.dao.UserDao;
import com.itstudent.model.AppUser;
import com.itstudent.model.LoginForm;
import com.itstudent.model.Result;
import com.itstudent.util.EncodeUtil;

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
            resp.getWriter().println(Result.send(EncodeUtil.encode(appUser.getUsername())));
        } else {
            resp.setStatus(400);
            resp.getWriter().println(Result.send("username or password is incorrect"));
        }
    }
}
