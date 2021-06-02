package agh.edu.pl.GroupCommunicator.servlets.groups;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.User;
import agh.edu.pl.GroupCommunicator.tables.pk.GroupMemberPK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*

    Handles removing user from a group.

 */

import java.io.IOException;

@WebServlet(name = "DeleteUserFromGroupServlet", urlPatterns = "/deleteUserFromGroup")
public class DeleteUserFromGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Session session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class, userId);
            Group group = session.get(Group.class, groupId);

            GroupMemberPK gmPk = new GroupMemberPK(user.getUserID(), group.getGroupID());
            GroupMember gm = session.get(GroupMember.class, gmPk);

            session.delete(gm);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("delete_fail", true);
            request.getRequestDispatcher("groupMembersList").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("delete_success", true);
        request.getRequestDispatcher("groupMembersList").forward(request, response);
    }
}
