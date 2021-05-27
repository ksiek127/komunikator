package agh.edu.pl.GroupCommunicator.servlets.emails.groupbox;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GroupboxServlet", value = "/groupbox")
public class GroupboxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        Map<Mail, String> emails = new HashMap<>();

        try (Session session = Main.getSession()) {
            Transaction tx = session.beginTransaction();
            List<Mail> mails = session.createQuery("select mail from Mail as mail where mail.group.groupID =:groupId" +
                            " order by mail.created desc", Mail.class)
                    .setParameter("groupId", groupId)
                    .getResultList();

            for (Mail mail: mails) {
                User user = session
                        .createQuery("select fromUser from Outbox o where o.mail.mailID=:mid", User.class)
                        .setParameter("mid", mail.getMailID())
                        .uniqueResult();

                emails.put(mail, user.getNameAndEmail());
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("emails", emails);
        request.getRequestDispatcher("/groupmails.jsp").forward(request, response);
    }
}
