package com.itstudent.servlet;

import com.google.gson.Gson;
import com.itstudent.dao.UserDao;
import com.itstudent.model.AppUser;
import com.itstudent.model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/private/profile"})
public class UserServlet extends HttpServlet {

    private UserDao dao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String username = (String) req.getAttribute("userPrinciple");
        try {
            AppUser user = dao.findByUsername(username);
            writer.println(Result.send(user));
        } catch (Exception ex) {
            resp.setStatus(500);
            writer.println(Result.send("get profile error"));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        try {
            AppUser user = gson.fromJson(req.getReader(), AppUser.class);
            String username = (String) req.getAttribute("userPrinciple");
            if (user.getUsername().equals(username) && dao.updateUser(user))
                writer.println(Result.send("update profile success"));
        }catch (Exception ex){
            resp.setStatus(500);
            writer.println(Result.send("update profile error"));
        }
    }
}
