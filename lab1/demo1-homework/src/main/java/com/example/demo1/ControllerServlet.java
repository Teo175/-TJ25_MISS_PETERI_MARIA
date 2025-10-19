package com.example.demo1;

import java.io.*;
import java.util.logging.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {

    // --- Classic Java Logger ---
    private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String selection = request.getParameter("selection");

        // --- Collect info ---
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String languages = request.getHeader("Accept-Language");

        // --- Log using classic Logger ---
        System.out.println(String.format(
                "Method=%s | IP=%s | UA=%s | Lang=%s | selection=%s",
                method, clientIp, userAgent, languages, selection));

        // --- Plain text response ---
        boolean plain = "txt".equalsIgnoreCase(request.getParameter("format")) ||
                (request.getHeader("Accept") != null &&
                        request.getHeader("Accept").contains("text/plain"));

        if (plain) {
            response.setContentType("text/plain; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(selection == null ? "" : selection);
            return;
        }

        // --- Forward to HTML pages ---
        String destination;
        if ("1".equals(selection)) {
            destination = "/page1.html";
        } else if ("2".equals(selection)) {
            destination = "/page2.html";
        } else {
            request.setAttribute("message", "Invalid selection.");
            destination = "/index.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }
}
