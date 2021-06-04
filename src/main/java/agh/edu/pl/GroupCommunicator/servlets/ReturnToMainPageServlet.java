package agh.edu.pl.GroupCommunicator.servlets;

/*

    Returns user to his profile page.
    Redirects to mainPage.jsp

 */

import agh.edu.pl.GroupCommunicator.LoggedUser;
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
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("user", LoggedUser.getUser());
        request.getRequestDispatcher("mainPage.jsp").forward(request, response);
    }
}
