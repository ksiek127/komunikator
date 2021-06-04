package agh.edu.pl.GroupCommunicator.servlets.groups.roles;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.GroupRank;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

/*

    Handles changing member's rank by an admin.

 */

@WebServlet(name = "ChangeRankServlet", urlPatterns = "/changeRank")
public class ChangeRankServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String groupRankString = request.getParameter("groupRank");
        GroupRank groupRank = null;
        if (groupRankString.equals("MEMBER")) {
            groupRank = GroupRank.MEMBER;
        } else if (groupRankString.equals("MODERATOR")) {
            groupRank = GroupRank.MODERATOR;
        } else {
            request.setAttribute("no_rank_choosen", true);
            request.getRequestDispatcher("groupMembersList").forward(request, response);
        }
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Session session = HibernateUtils.getSession();
        try {
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class, userId);
            Group group = session.get(Group.class, groupId);

            GroupMember gm = new GroupMember(user, group, groupRank);

            session.update(gm);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("change_rank_fail", true);
            request.getRequestDispatcher("groupMembersList").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("change_rank_success", true);
        request.getRequestDispatcher("groupMembersList").forward(request, response);
    }
}
