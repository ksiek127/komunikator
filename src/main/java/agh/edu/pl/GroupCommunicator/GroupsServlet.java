package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.Mail;
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
import java.util.List;

@SuppressWarnings("unchecked")
@WebServlet(name = "groups", value = "/groups")
public class GroupsServlet extends HttpServlet {
    public GroupsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        List<Group> groups = null;
        Transaction tx = null;
        User user = null;
        try(Session session = Main.getSession()){
            tx = session.beginTransaction();
            List<User> usersList = session.createQuery("from User as user where user.email=:userEmail", User.class)
                    .setParameter("userEmail", email)
                    .getResultList();
            user = usersList.isEmpty() ? null : usersList.get(0);
            assert user != null;
            int userId = user.getUserID();
            groups = session.createQuery("select group from GroupMember groupMember where groupMember.user.userID = " + userId, Group.class)
                    .getResultList();
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        request.setAttribute("groups", groups);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/groups.jsp");
        dispatcher.forward(request, response);
    }
}
