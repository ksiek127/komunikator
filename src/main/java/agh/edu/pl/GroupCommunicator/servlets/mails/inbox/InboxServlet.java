package agh.edu.pl.GroupCommunicator.servlets.mails.inbox;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Mail;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "InboxServlet", value = "/inbox")
public class InboxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = Main.getUser().getEmail();
        List<Mail> emails_new = null;
        List<Mail> emails_read = null;
        try (Session session = Main.getSession()) {
            User user = Main.getUser();
            Transaction tx = session.beginTransaction();
            int userId = user.getUserID();
            emails_new = session.createQuery("select mail from Inbox inbox where inbox.toUser.userID =:userId " +
                    "and inbox.wasRead = false", Mail.class)
                    .setParameter("userId", userId)
                    .getResultList();
            emails_read = session.createQuery("select mail from Inbox inbox where inbox.toUser.userID =:userId" +
                    " and inbox.wasRead = true", Mail.class)
                    .setParameter("userId", userId)
                    .getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("new_emails", emails_new);
        request.setAttribute("old_emails", emails_read);
        request.getRequestDispatcher("/inbox.jsp").forward(request, response);
    }
}
