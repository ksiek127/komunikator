package agh.edu.pl.GroupCommunicator.servlets.groups.requests;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

/*

    Handles sending requests to join a group.

 */

@WebServlet(name = "SendRequestServlet", urlPatterns = "/sendRequest")
public class SendRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("group_id"));

        Session session = HibernateUtils.getSession();
        try {
            Transaction tx = session.beginTransaction();

            Group group = session.get(Group.class, groupId);

            GroupRequest gr = new GroupRequest(group, LoggedUser.getUser());

            session.save(gr);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("request_failed", true);
            request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("group_name", request.getParameter("group_name"));
        request.setAttribute("request_sent", true);
        request.getRequestDispatcher("searchGroups.jsp").forward(request, response);
    }
}
