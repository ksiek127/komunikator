package agh.edu.pl.GroupCommunicator.servlets.groups;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.GroupRank;
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

@WebServlet(name = "LeaveGroupServlet", urlPatterns = "/leaveGroup")
public class LeaveGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("group_id"));
        String returnPage = request.getParameter("returnPage");

        Group group = null;
        GroupMember gm = null;
        List<GroupMember> gms = null;
        Session session = HibernateUtils.getSession();
        try {
            Transaction tx = session.beginTransaction();

            group = session.get(Group.class, groupId);

            gms = session
                    .createQuery("from GroupMember as gm where gm.group.groupID=:gId", GroupMember.class)
                    .setParameter("gId", groupId)
                    .getResultList();

            GroupMemberPK gmPk = new GroupMemberPK(LoggedUser.getUser().getUserID(), groupId);
            gm = session.get(GroupMember.class, gmPk);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("leave_failed", true);
            request.getRequestDispatcher(returnPage).forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }


        if (gm == null || group == null) {
            request.setAttribute("leave_failed", true);
            request.getRequestDispatcher(returnPage).forward(request, response);
        } else {
            if (gm.getGroupRank() == GroupRank.ADMIN) {
                request.setAttribute("group_admin", true);
            } else {
                request.setAttribute("group_admin", false);
            }
            if (gms != null && gms.size() > 1) {
                request.setAttribute("other_members", true);
            }
            request.setAttribute("returnPage", returnPage);
            request.setAttribute("group", group);
            request.getRequestDispatcher("leaveGroup.jsp").forward(request, response);
        }
    }
}
