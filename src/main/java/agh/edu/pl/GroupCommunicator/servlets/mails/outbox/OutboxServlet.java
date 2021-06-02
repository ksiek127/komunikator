package agh.edu.pl.GroupCommunicator.servlets.mails.outbox;

/*

    Gets a list of user's outgoing mails from database
    Redirects to outbox.jsp.

 */

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
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
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "OutboxServlet", value = "/outbox")
public class OutboxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Mail> emails = null;
        try (Session session = HibernateUtils.getSession()) {
            User user = LoggedUser.getUser();
            Transaction tx = session.beginTransaction();
            int userId = user.getUserID();
            emails = session
                    .createQuery("select mail from Outbox outbox where outbox.fromUser.userID =:userId " +
                            "and outbox.wasDeleted = false", Mail.class)
                    .setParameter("userId", userId)
                    .getResultList();

            Comparator<Mail> byCreatedDate = Comparator.comparing(Mail::getCreated).reversed();
            emails.sort(byCreatedDate);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("emails", emails);
        request.getRequestDispatcher("/outbox.jsp").forward(request, response);
    }
}
