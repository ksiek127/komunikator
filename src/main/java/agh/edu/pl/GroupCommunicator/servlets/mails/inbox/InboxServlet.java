package agh.edu.pl.GroupCommunicator.servlets.mails.inbox;

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InboxServlet", value = "/inbox")
public class InboxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<Mail, String> mailsNewMap = new LinkedHashMap<>();
        Map<Mail, String> mailsReadMap = new LinkedHashMap<>();
        try (Session session = HibernateUtils.getSession()) {
            User user = LoggedUser.getUser();
            Transaction tx = session.beginTransaction();
            int userId = user.getUserID();
            List<Mail> mails_new = session.createQuery("select mail from Inbox inbox where inbox.toUser.userID =:userId " +
                    "and inbox.wasRead = false", Mail.class)
                    .setParameter("userId", userId)
                    .getResultList();
            List<Mail> mails_read = session.createQuery("select mail from Inbox inbox where inbox.toUser.userID =:userId" +
                    " and inbox.wasRead = true", Mail.class)
                    .setParameter("userId", userId)
                    .getResultList();

            Comparator<Mail> byCreatedDate = Comparator.comparing(Mail::getCreated).reversed();
            mails_new.sort(byCreatedDate);
            mails_read.sort(byCreatedDate);

            for (Mail mail: mails_new) {
                User fromUser = session
                        .createQuery("select fromUser from Outbox o where o.mail.mailID=:mid", User.class)
                        .setParameter("mid", mail.getMailID())
                        .uniqueResult();

                mailsNewMap.put(mail, fromUser.getNameAndEmail());
            }

            for (Mail mail: mails_read) {
                User fromUser = session
                        .createQuery("select fromUser from Outbox o where o.mail.mailID=:mid", User.class)
                        .setParameter("mid", mail.getMailID())
                        .uniqueResult();

                mailsReadMap.put(mail, fromUser.getNameAndEmail());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("new_mails", mailsNewMap);
        request.setAttribute("old_mails", mailsReadMap);
        request.getRequestDispatcher("/inbox.jsp").forward(request, response);
    }
}
