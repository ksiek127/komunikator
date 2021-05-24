package agh.edu.pl.GroupCommunicator.servlets.groups;

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

@WebServlet(name = "LeaveGroupHandlerServlet", urlPatterns = "/leaveGroupHandler")
public class LeaveGroupHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int groupId = Integer.parseInt(request.getParameter("group_id"));

        Session session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();

            GroupMemberPK gmPk = new GroupMemberPK(Main.getUser().getUserID(), groupId);
            GroupMember gm = session.get(GroupMember.class, gmPk);

            session.delete(gm);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("group_leave_failed", true);
            request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("group_leave_success", true);
        request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
    }
}