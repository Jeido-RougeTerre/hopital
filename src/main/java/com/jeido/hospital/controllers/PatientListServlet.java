package com.jeido.hospital.controllers;

import com.jeido.hospital.entities.Patient;
import com.jeido.hospital.services.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "multi-list-servlet", value ="/list/*")
public class PatientListServlet extends HttpServlet {
    private PatientService service;

    @Override
    public void init() throws ServletException {
        service = new PatientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        req.setAttribute("session",
                session.getAttribute("isLogged") != null && (boolean) session.getAttribute("isLogged")
        );

        String pathInfo =(req.getPathInfo() == null || req.getPathInfo().substring(1).isEmpty() ? "" : req.getPathInfo());

        if (!pathInfo.startsWith("/")) {
            list(req, resp);
            return;
        }

        switch (pathInfo.substring(1)) {
            case "add"-> addGet(req, resp);
            case "search" -> search(req, resp);
            default -> list(req, resp);
        }

    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = service.findAll();
        req.setAttribute("patients", patients);
        req.setAttribute("mode", "list");
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }

    public void addGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = service.findAll();
        req.setAttribute("patients", patients);
        req.setAttribute("mode", "add");
        req.setAttribute("patient", Patient.builder().name("").phone("").birthDate(LocalDate.now()).build());

        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = service.findByName(req.getParameter("name"));
        req.setAttribute("patients", patients);
        req.setAttribute("mode", "search");
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo =(req.getPathInfo() == null || req.getPathInfo().substring(1).isEmpty() ? "" : req.getPathInfo());

        if (!pathInfo.startsWith("/")) {
            doGet(req, resp);
            return;
        }

        if (pathInfo.substring(1).equals("add")) {
            addPost(req, resp);
        } else {
            doGet(req, resp);
        }
    }

    protected void addPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));

        service.create(name, phone, birthDate);
        req.setAttribute("mode", "");
        List<Patient> patients = service.findAll();
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
    }
}
