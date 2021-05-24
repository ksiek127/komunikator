package agh.edu.pl.GroupCommunicator.servlets.emails;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.Inbox;
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
import java.util.Date;
import java.util.List;

@WebServlet(name = "MessageServlet", value = "/message-servlet")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mailId = Integer.parseInt(request.getParameter("mailId"));
        Mail mail;
        String title;
        String msg;
        int groupId;
        String groupName;
        Date date;

        Session session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();

            mail = session.createQuery("from Mail as mail where mail.mailID = " + mailId, Mail.class)
                    .getResultList().get(0);

            title = mail.getTitle();
            date = mail.getCreated();
            msg = mail.getMessage();
            groupId = mail.getGroupId();

            Group group = session.createQuery("from Group as group where group.groupID = " + groupId, Group.class)
                    .getResultList().get(0);
            groupName = group.getName();

            int userId = Main.getUser().getUserID();
            Inbox inboxMail = session.createQuery("from Inbox as inbox where inbox.toUser = " + userId + " and inbox.mail = " + mailId, Inbox.class)
                    .getResultList().get(0);

            inboxMail.setWasRead(true);

            session.update(inboxMail);
            tx.commit();
        } finally {
            session.close();
        }
        request.setAttribute("title", title);
        request.setAttribute("msg", msg);
        request.setAttribute("date", date);
        request.setAttribute("groupName", groupName);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
