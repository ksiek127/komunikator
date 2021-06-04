/*

    When user tries to leave the group, this servlet checks if he's a regular member or a moderator and he can leave,
    or if he's an admin and him leaving the group would mean deleting the entire group.

 */


package agh.edu.pl.GroupCommunicator.servlets.account;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckIfGroupAdminServlet", urlPatterns = "/checkIfGroupAdmin")
public class CheckIfGroupAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = LoggedUser.getUser();
        Session session = HibernateUtils.getSession();
        List<Group> groupsWhereAdmin = null;
        try {

            groupsWhereAdmin = session
                    .createQuery("select gm.group from GroupMember as gm where gm.user.userID =: uid and " +
                            "gm.groupRank = 'ADMIN'", Group.class)
                    .setParameter("uid", user.getUserID())
                    .getResultList();

        } catch (Throwable ex) {
            request.setAttribute("delete_error", true);
            request.getRequestDispatcher("/returnToMainPage").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        if (groupsWhereAdmin.size() > 0) {
            request.setAttribute("groups", groupsWhereAdmin);
            request.getRequestDispatcher("checkGroupsBeforeAccountDeletion.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/removeUser").forward(request, response);
        }
    }
}
