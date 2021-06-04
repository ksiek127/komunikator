package agh.edu.pl.GroupCommunicator.servlets.mails;

/*

    Redirects user to page where he can send new message.
    Redirects to sendMail.jsp

 */

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
            throws ServletException, IOException {
        request.setAttribute("groupId", request.getParameter("groupId"));
        request.getRequestDispatcher("sendMail.jsp").forward(request, response);
    }
}
