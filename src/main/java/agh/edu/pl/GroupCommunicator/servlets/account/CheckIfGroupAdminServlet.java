package agh.edu.pl.GroupCommunicator.servlets.account;

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

import java.io.IOException;
import java.util.ArrayList;
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
        User user = Main.getUser();
        Session session = Main.getSession();
        List<Group> groupsWhereAdmin = null;
        try {

            groupsWhereAdmin = session
                    .createQuery("select gm.group from GroupMember as gm where gm.user.userID =: uid and " +
                            "gm.groupRank = 'ADMIN'", Group.class)
                    .setParameter("uid", user.getUserID())
                    .getResultList();

        } catch(Throwable ex) {
            request.setAttribute("delete_error", true);
            request.getRequestDispatcher("/returnToMainPage").forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        if (groupsWhereAdmin.size() > 0) {
            request.setAttribute("groups", groupsWhereAdmin);
            request.getRequestDispatcher("groupscheckbeforeaccountdeletion.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/removeUser").forward(request, response);
        }
    }
}