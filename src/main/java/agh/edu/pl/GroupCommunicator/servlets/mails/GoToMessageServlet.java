package agh.edu.pl.GroupCommunicator.servlets.mails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GoToMessageServlet", value = "/go-to-message")
public class GoToMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("groupId", request.getParameter("groupId"));
        request.getRequestDispatcher("sendmessage.jsp").forward(request, response);
    }
}