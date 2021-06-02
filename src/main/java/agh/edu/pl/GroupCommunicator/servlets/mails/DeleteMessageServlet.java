package agh.edu.pl.GroupCommunicator.servlets.mails;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.Inbox;
import agh.edu.pl.GroupCommunicator.tables.Outbox;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "DeleteMessage", value = "/delete-message")
public class DeleteMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int mailId = Integer.parseInt(request.getParameter("mailId"));
        try (Session session = HibernateUtils.getSession()) {
            User user = LoggedUser.getUser();
            Transaction tx = session.beginTransaction();
            String where = request.getParameter("where");
            if (where.equals("inbox")) {
                Inbox inbox = session
                        .createQuery("from Inbox as i where i.mail.mailID = :mailId and i.toUser.userID =" +
                                ":userId", Inbox.class)
                        .setParameter("userId", user.getUserID())
                        .setParameter("mailId", mailId)
                        .uniqueResult();

                session.delete(inbox);
            } else if (where.equals("outbox")) {
                Outbox outbox = session
                        .createQuery("from Outbox as o where o.mail.mailID = :mailId and" +
                                "o.fromUser.userID = : userId", Outbox.class)
                        .setParameter("userId", user.getUserID())
                        .setParameter("mailId", mailId)
                        .uniqueResult();

                outbox.setWasDeleted(true);
                session.update(outbox);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/returnToMainPage").forward(request, response);
    }
}
