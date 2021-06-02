package agh.edu.pl.GroupCommunicator.servlets.account;

/*

    Servlet that safely removes user from the database.

 */

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Inbox;
import agh.edu.pl.GroupCommunicator.tables.Outbox;
import agh.edu.pl.GroupCommunicator.tables.User;
import agh.edu.pl.GroupCommunicator.tables.pk.OutboxPK;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

import static agh.edu.pl.GroupCommunicator.DeletedAccountUser.getDeletedAccountUser;

@WebServlet(name = "RemoveUserServlet", value = "/removeUser")
public class RemoveUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = Main.getUser();

        Session session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();

            List<Outbox> outbox = session
                    .createQuery("from Outbox as o where o.fromUser.userID =: uid", Outbox.class)
                    .setParameter("uid", user.getUserID())
                    .getResultList();

            User deletedUser = getDeletedAccountUser(session);

            for (Outbox item: outbox) {
                List<Inbox> inbox = session
                        .createQuery("from Inbox as i where i.mail.mailID =: mailId", Inbox.class)
                        .setParameter("mailId", item.getMail().getMailID())
                        .getResultList();

                if (inbox.size() > 0) {
                    Outbox itemDeletedAccount = new Outbox(item.getMail(), deletedUser);
                    session.save(itemDeletedAccount);
                }
                session.delete(item);
            }

            tx.commit();
        } catch (Exception e) {
            request.setAttribute("error_while_removing_user", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            e.printStackTrace();
        } finally {
            session.close();
        }

        session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            request.setAttribute("error_while_removing_user", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            e.printStackTrace();
        } finally {
            session.close();
        }
        request.setAttribute("removed_user", true);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
