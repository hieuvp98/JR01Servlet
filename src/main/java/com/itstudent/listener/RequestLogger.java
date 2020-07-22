package com.itstudent.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestLogger implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        long endTime = System.currentTimeMillis();
        long startTime = (long) request.getAttribute("startTime");
        System.out.println("Process time: " + ( endTime - startTime));
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        System.out.println("Request : " + request.getRequestURI() +
                " method: " + request.getMethod());
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
    }
}
