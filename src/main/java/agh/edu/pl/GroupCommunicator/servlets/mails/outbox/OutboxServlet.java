package agh.edu.pl.GroupCommunicator.servlets.mails.outbox;

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

@WebServlet(name = "OutboxServlet", value = "/outbox")
public class OutboxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = Main.getUser().getEmail();
        List<Mail> emails = null;
        try (Session session = Main.getSession()) {
            User user = Main.getUser();
             Transaction tx = session.beginTransaction();
            assert user != null;
            int userId = user.getUserID();
            emails = session.createQuery("select mail from Outbox outbox where outbox.fromUser.userID =:userId",
                    Mail.class)
                    .setParameter("userId", userId)
                    .getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("emails", emails);
        request.getRequestDispatcher("/outbox.jsp").forward(request, response);
    }
}
