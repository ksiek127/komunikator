package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Inbox;
import agh.edu.pl.GroupCommunicator.tables.Mail;
import agh.edu.pl.GroupCommunicator.tables.Outbox;
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
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unchecked")
@WebServlet(name = "inbox", value = "/inbox")
public class InboxServlet extends HttpServlet {

    public InboxServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String email = request.getParameter("email");
        List<Mail> emails = null;
        User user = null;
        Transaction tx = null;
        try(Session session = Main.getSession()){
            tx = session.beginTransaction();
            List<User> usersList = session.createQuery("from User as user where user.email=:userEmail", User.class)
                    .setParameter("userEmail", email)
                    .getResultList();
            user = usersList.isEmpty() ? null : usersList.get(0);
            assert user != null;
            int userId = user.getUserID();
            emails = session.createQuery("select mail from Outbox outbox where outbox.toUser = " + userId, Mail.class)
                    .getResultList();
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        request.setAttribute("emails", emails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inbox.jsp");
        dispatcher.forward(request, response);
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException{
//        doGet(request, response);
//    }

//    @SuppressWarnings("unchecked")
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException{
//        String usrEmail = request.getParameter("email");
//        Transaction tx = null;
//        List<Mail> emails = null;
//        try(Session session = Main.getSession()){
//            tx = session.beginTransaction();
////            int userId = session.createQuery("select userID from User as user" +
////                    "where user.email=:userEmail", User.class)
////                    .setParameter("userEmail", usrEmail)
////                    .getFirstResult();
////            User user = session.createQuery("from User as user" +
////                    "where user.email=:userEmail", User.class)
////                    .setParameter("userEmail", usrEmail)
////                    .getResultList().get(0);
////            emails = session.createQuery("select title, message from Mail as mail" +
////                    "inner join Inbox as inbox with inbox.mailID = mail.mailID" +
////                    "inner join Outbox as outbox with outbox.mailID = mail.mailID" +
////                    "where outbox.toUser =:userId", Mail.class)
////                    .setParameter("userId", user.getUserID())
////                    .getResultList();
//            emails = session.createQuery("from Mail", Mail.class).getResultList();
//            tx.commit();
//        } catch (Exception e){
//            if(tx != null){
//                tx.rollback();
//            }
//            e.printStackTrace();
//        }
//        System.out.println("xd");
//        request.setAttribute("emails", emails);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("inbox.jsp");
//        dispatcher.forward(request, response);
//    }
}
