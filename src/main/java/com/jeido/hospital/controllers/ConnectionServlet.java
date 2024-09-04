package com.jeido.hospital.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "conection-servlet", value = "/auth")
public class ConnectionServlet extends HttpServlet {
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean isLogged = session.getAttribute("isLogged") != null && (boolean) session.getAttribute("isLogged");


        if (isLogged) {
            req.getRequestDispatcher("/list").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/auth.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isLogged = username.equals(USERNAME) && password.equals(PASSWORD);

        session.setAttribute("isLogged", isLogged);
        if (isLogged) {
            resp.sendRedirect("list");
        } else {
            doGet(req, resp);
        }
    }
}
