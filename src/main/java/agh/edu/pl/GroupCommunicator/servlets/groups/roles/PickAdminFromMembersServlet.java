package agh.edu.pl.GroupCommunicator.servlets.groups.roles;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PickAdminFromMembersServlet", urlPatterns = "/pickAdminFromMembers")
public class PickAdminFromMembersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int groupId = Integer.parseInt(request.getParameter("group_id"));

        Session session = Main.getSession();
        List<User> groupMembers = null;
        try {
            Transaction tx = session.beginTransaction();

            List<GroupMember> gms = session
                    .createQuery("from GroupMember as gm where gm.group.groupID=:gId and gm.user.userID!=:uId",
                            GroupMember.class)
                    .setParameter("gId", groupId)
                    .setParameter("uId", Main.getUser().getUserID())
                    .getResultList();

            groupMembers = new ArrayList<>();

            User user;
            for (GroupMember gm: gms) {
                user = session.get(User.class, gm.getUserId());
                groupMembers.add(user);
            }

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("group_leave_failed", true);
            request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        if (groupMembers != null) {
            request.setAttribute("groupId", groupId);
            request.setAttribute("group_members", groupMembers);
            request.getRequestDispatcher("picknewgroupadmin.jsp").forward(request, response);
        } else {
            request.setAttribute("group_leave_failed", true);
            request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
        }
    }
}
