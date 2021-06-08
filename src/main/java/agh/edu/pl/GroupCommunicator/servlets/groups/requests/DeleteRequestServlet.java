package agh.edu.pl.GroupCommunicator.servlets.groups.requests;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupRequest;
import agh.edu.pl.GroupCommunicator.tables.pk.GroupRequestPK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

/*

    Deletes safely user's request to join a group.

 */

@WebServlet(name = "DeleteRequestServlet", urlPatterns = "/deleteRequest")
public class DeleteRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("group_id"));

        Session session = HibernateUtils.getSession();
        try {
            Transaction tx = session.beginTransaction();

            Group group = session.get(Group.class, groupId);

            GroupRequestPK grPk = new GroupRequestPK(LoggedUser.getUser().getUserID(), groupId);
            GroupRequest gr = session.get(GroupRequest.class, grPk);

            session.delete(gr);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("request_delete_failed", true);
            request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
        } finally {
            session.close();
        }

        request.setAttribute("request_deleted", true);
        request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
    }
}
