package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Mail;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unchecked")
public class InboxServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String email = request.getParameter("email");
        Transaction tx = null;
        List<Mail> emails = null;
        try(Session session = Main.getSession()){
            tx = session.beginTransaction();
            emails = session.createQuery("from Mail as mail" +
                    "inner join Inbox as inbox with inbox.mail = mail" +
                    "inner join Outbox as outbox with outbox.mail = mail" +
                    "where outbox.toUser =:userEmail")
                    .setParameter("userEmail", email)
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
