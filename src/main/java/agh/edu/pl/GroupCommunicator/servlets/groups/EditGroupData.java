package agh.edu.pl.GroupCommunicator.servlets.groups;

/*

    Redirects to editGroupData.jsp with a given groupId provided

 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditGroupDataServlet", value = "/edit-group-data")
public class EditGroupData extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("groupId", request.getParameter("groupId"));
        request.getRequestDispatcher("editGroupData.jsp").forward(request, response);
    }
}
