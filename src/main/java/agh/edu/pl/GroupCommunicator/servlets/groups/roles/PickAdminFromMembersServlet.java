package agh.edu.pl.GroupCommunicator.servlets.groups.roles;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
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

        String returnPage = request.getParameter("returnPage");

        Session session = Main.getSession();
        String groupName = "";
        List<User> groupMembers = null;
        try {
            Transaction tx = session.beginTransaction();

            int groupId = Integer.parseInt(request.getParameter("group_id"));

            groupMembers = session
                    .createQuery("select gm.user from GroupMember as gm where gm.group.groupID=:gId " +
                                    "and gm.user.userID!=:uId", User.class)
                    .setParameter("gId", groupId)
                    .setParameter("uId", Main.getUser().getUserID())
                    .getResultList();

            groupName = session.get(Group.class, groupId).getName();

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("group_leave_failed", true);
            request.getRequestDispatcher(returnPage).forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        if (groupMembers != null) {
            request.setAttribute("group_name", groupName);
            request.setAttribute("groupId", request.getParameter("group_id"));
            if (groupMembers.size() > 0) {
                request.setAttribute("group_members", groupMembers);
            } else {
                request.setAttribute("group_empty", true);
            }
            request.setAttribute("returnPage", returnPage);
            request.getRequestDispatcher("picknewgroupadmin.jsp").forward(request, response);
        } else {
            request.setAttribute("group_leave_failed", true);
            request.getRequestDispatcher(returnPage).forward(request, response);
        }
    }
}
