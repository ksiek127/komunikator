package agh.edu.pl.GroupCommunicator.servlets.groups;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GroupsServlet", value = "/groups")
public class GroupsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = Main.getUser().getEmail();
        List<Group> groupsMember = null; //grupy, w ktorych jestem zwyklym czlonkiem
        Map<Group, String> mapGroupsAdminMod = new LinkedHashMap<>();
        Transaction tx = null;
        User user;
        try (Session session = Main.getSession()) {
            tx = session.beginTransaction();
            List<User> usersList = session.createQuery("from User as user where user.email=:userEmail", User.class)
                    .setParameter("userEmail", email)
                    .getResultList();
            user = usersList.isEmpty() ? null : usersList.get(0);
            assert user != null;
            int userId = user.getUserID();
            groupsMember = session.createQuery("select group from GroupMember groupMember where " +
                            "groupMember.user.userID = " + userId + " and groupMember.groupRank = 'MEMBER'",
                    Group.class)
                    .getResultList();
            List<Group> groupsAdmin = session.createQuery("select group from GroupMember groupMember where " +
                            "groupMember.user.userID = " + userId + " and groupMember.groupRank = 'ADMIN'",
                    Group.class)
                    .getResultList();
            List<Group> groupsModerator = session.createQuery("select group from GroupMember groupMember where " +
                            "groupMember.user.userID = " + userId + " and groupMember.groupRank = 'MODERATOR'",
                    Group.class)
                    .getResultList();

            Comparator<Group> byName = Comparator.comparing(Group::getNameToLower);
            groupsAdmin.sort(byName);
            groupsModerator.sort(byName);
            groupsMember.sort(byName);

            for (Group group : groupsAdmin) {
                mapGroupsAdminMod.put(group, "ADMIN");
            }
            for (Group group : groupsModerator) {
                mapGroupsAdminMod.put(group, "MODERATOR");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        request.setAttribute("groupsMember", groupsMember);
        request.setAttribute("groupsMap", mapGroupsAdminMod);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/groups.jsp");
        dispatcher.forward(request, response);
    }
}
