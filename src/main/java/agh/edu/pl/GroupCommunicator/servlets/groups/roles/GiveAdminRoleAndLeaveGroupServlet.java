package agh.edu.pl.GroupCommunicator.servlets.groups.roles;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.GroupRank;
import agh.edu.pl.GroupCommunicator.tables.User;
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

/*

    When admin leaves the group, he can give his rank to someone else before leaving.

 */

@WebServlet(name = "GiveAdminRoleAndLeaveGroupServlet", urlPatterns = "/giveAdminRoleAndLeaveGroup")
public class GiveAdminRoleAndLeaveGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int new_admin_user_id = Integer.parseInt(request.getParameter("user_id"));
        int group_id = Integer.parseInt(request.getParameter("group_id"));
        String returnPage = request.getParameter("returnPage");

        Session session = HibernateUtils.getSession();
        List<User> groupMembers = null;
        try {
            Transaction tx = session.beginTransaction();

//            give admin role
            GroupMemberPK gmPk = new GroupMemberPK(new_admin_user_id, group_id);
            GroupMember gm = session.get(GroupMember.class, gmPk);

            gm.setGroupRank(GroupRank.ADMIN);

            session.update(gm);

//            leave group
            gmPk = new GroupMemberPK(LoggedUser.getUser().getUserID(), group_id);
            gm = session.get(GroupMember.class, gmPk);

            session.delete(gm);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("group_leave_and_admin_change_failed", true);
            request.getRequestDispatcher(returnPage).forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("group_leave_and_admin_change_success", true);
        request.getRequestDispatcher(returnPage).forward(request, response);
    }
}
