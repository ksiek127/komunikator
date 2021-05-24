package agh.edu.pl.GroupCommunicator.servlets;

import agh.edu.pl.GroupCommunicator.Main;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ReturnToMainPageServlet", urlPatterns = "/returnToMainPage")
public class ReturnToMainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("user", Main.getUser());
        request.getRequestDispatcher("mainpage.jsp").forward(request, response);
    }
}