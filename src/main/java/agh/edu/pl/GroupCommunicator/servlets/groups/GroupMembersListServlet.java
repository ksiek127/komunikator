package agh.edu.pl.GroupCommunicator.servlets.groups;

/*

    Loads group members from a given group and redirects to usersInGroup.jsp with members list assigned

 */

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
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

@WebServlet(name = "GroupMembersListServlet", urlPatterns = "/groupMembersList")
public class GroupMembersListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String groupName = request.getParameter("groupName");

        List<GroupMember> groupMembers = null;
        GroupMember currentUser = null;
        Session session = HibernateUtils.getSession();
        try {
            Transaction tx = session.beginTransaction();

            GroupMemberPK gmPk = new GroupMemberPK(LoggedUser.getUser().getUserID(), groupId);
            currentUser = session.get(GroupMember.class, gmPk);

            if (currentUser.getGroupRank().equals(GroupRank.MEMBER)) {
                request.setAttribute("member", true);
            } else if (currentUser.getGroupRank().equals(GroupRank.ADMIN)) {
                request.setAttribute("admin", true);
            } else if (currentUser.getGroupRank().equals(GroupRank.MODERATOR)) {
                request.setAttribute("moderator", true);
            }

            groupMembers = session
                    .createQuery("from GroupMember as gm where gm.user.userID !=: uid and" +
                            " gm.group.groupID =: gid", GroupMember.class)
                    .setParameter("gid", groupId)
                    .setParameter("uid", LoggedUser.getUser().getUserID())
                    .getResultList();

            tx.commit();
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("groupMembers", groupMembers);
        request.setAttribute("groupId", groupId);
        request.setAttribute("group_name", groupName);
        request.getRequestDispatcher("usersInGroup.jsp").forward(request, response);
    }
}
