package agh.edu.pl.GroupCommunicator.servlets.groups;

/*

    Handles deleting a group.

 */

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.pk.GroupMemberPK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteGroupServlet", urlPatterns = "/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("group_id"));
        String returnPage = request.getParameter("returnPage");

        if (returnPage == null) {
            returnPage = "/returnToMainPage";
        }

        Group group;
        Session session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();

            group = session.get(Group.class, groupId);

            session.delete(group);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("group_remove_failed", true);
            request.getRequestDispatcher(returnPage).forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("group_remove_success", true);
        request.getRequestDispatcher(returnPage).forward(request, response);
    }
}
