package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Mail;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unchecked")
public class OutboxServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String usrEmail = request.getParameter("email");
        Transaction tx = null;
        List<Mail> emails = null;
        try(Session session = Main.getSession()){
            tx = session.beginTransaction();
            int userId = session.createQuery("select userID from User as user" +
                    "where user.email=:userEmail")
                    .setParameter("userEmail", usrEmail)
                    .getFirstResult();
            emails = session.createQuery("select title, message from Mail as mail" +
                    "inner join Inbox as inbox with inbox.mailID = mail.mailID" +
                    "inner join Outbox as outbox with outbox.mailID = mail.mailID" +
                    "where outbox.toUser =:userId")
                    .setParameter("userId", userId)
                    .getResultList();
            tx.commit();
        } catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        request.setAttribute("emails", emails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("inboxemail.jsp");
        dispatcher.forward(request, response);
    }
}
