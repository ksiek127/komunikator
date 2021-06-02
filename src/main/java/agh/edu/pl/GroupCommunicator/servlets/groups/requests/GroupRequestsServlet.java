package agh.edu.pl.GroupCommunicator.servlets.groups.requests;

/*

    Gets requests to join the group with ID given as a parameter, and forwards to page where all requests to join this
    group are displayed

 */

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupRank;
import agh.edu.pl.GroupCommunicator.tables.GroupRequest;
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

@WebServlet(name = "GroupRequestsServlet", urlPatterns = "/groupRequests")
public class GroupRequestsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String groupName = request.getParameter("groupName");

        Session session = Main.getSession();
        List<User> users = new ArrayList<>();
        try {
            Transaction tx = session.beginTransaction();
            users = session
                    .createQuery("select user from GroupRequest as gr where gr.group.groupID =:gid" +
                            " and gr.user.userID !=:uid", User.class)
                    .setParameter("gid", groupId)
                    .setParameter("uid", Main.getUser().getUserID())
                    .getResultList();

            tx.commit();
        } catch (Throwable ex){
            ex.printStackTrace();
        } finally {
            session.close();
        }
        request.setAttribute("users", users);
        request.setAttribute("groupId", groupId);
        request.setAttribute("group_name", groupName);
        request.getRequestDispatcher("/grouprequests.jsp").forward(request, response);
    }
}
