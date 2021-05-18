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
        List<Group> groupsMember = null; //grupy, w ktorych jestem zwyklym czlonkiem
        List<Group> groupsAdmin = null; //jestem adminem
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
            groupsMember = session.createQuery("select group from GroupMember groupMember where groupMember.user.userID = " + userId +" and groupMember.groupRank = MEMBER", Group.class)
                    .getResultList();
            groupsAdmin = session.createQuery("select group from GroupMember groupMember where groupMember.user.userID = " + userId +" and groupMember.groupRank = ADMIN", Group.class)
                    .getResultList();
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        request.setAttribute("groupsMember", groupsMember);
        request.setAttribute("groupsAdmin", groupsAdmin);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/groups.jsp");
        dispatcher.forward(request, response);
    }
}
