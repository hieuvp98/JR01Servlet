package com.itstudent.servlet;

import com.google.gson.Gson;
import com.itstudent.dao.UserDao;
import com.itstudent.model.RegisterForm;
import com.itstudent.model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/register")
public class RegisterServlet extends HttpServlet {

    private UserDao dao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        RegisterForm form = gson.fromJson(req.getReader(), RegisterForm.class);
        PrintWriter writer = resp.getWriter();
        if (dao.checkExist(form.getUsername())){
            writer.println(Result.send("username was existed"));
        }else {
            if(dao.insertUser(form))
                writer.println(Result.send("register success"));
            else
                writer.println(Result.send("register fail"));
        }
    }
}
