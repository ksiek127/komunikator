package agh.edu.pl.GroupCommunicator.servlets.groups.requests;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
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

    Handles rejecting requests to join a group.

 */

@WebServlet(name = "RejectRequestServlet", urlPatterns = "/rejectRequest")
public class RejectRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Session session = HibernateUtils.getSession();
        try {
            Transaction tx = session.beginTransaction();

            GroupRequestPK grPk = new GroupRequestPK(userId, groupId);
            GroupRequest gr = session.get(GroupRequest.class, grPk);

            session.delete(gr);

            tx.commit();
        } catch (Throwable ex) {
            request.setAttribute("reject_fail", true);
            request.getRequestDispatcher("groupRequests").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }
        request.setAttribute("reject_success", true);
        request.getRequestDispatcher("groupRequests").forward(request, response);
    }
}
