package agh.edu.pl.GroupCommunicator.servlets.mails;

/*

    Creates new mail with a given title and message.
    Sends created mail to members of chosen group (add information to inbox)
    Redirects to messageSent.jsp on success and to sendMail.jsp with error on failure

 */

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SendMessageServlet", value = "/send-message")
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = LoggedUser.getUser();
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String groupName = null;
        if (title.isEmpty() || message.isEmpty()) {
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/sendMail.jsp").forward(request, response);
        } else {
            List<GroupMember> members;
            try (Session session = HibernateUtils.getSession()) {
                Transaction tx = session.beginTransaction();

                Group group = session.get(Group.class, groupId);

                groupName = group.getName();

                Mail mail = new Mail(message, title, group);
                Outbox outbox = new Outbox(mail, user);
                session.save(mail);
                members = session
                        .createQuery("from GroupMember as gm where gm.group.groupID=:gId", GroupMember.class)
                        .setParameter("gId", groupId)
                        .getResultList();
                System.out.println(members);
                for (GroupMember member : members) {
                    if (member.getUser().getUserID() != LoggedUser.getUser().getUserID()) {
                        System.out.println(member.getUser().getUserID());
                        Inbox inbox = new Inbox(mail, member.getUser());
                        session.save(inbox);
                    }
                }
                session.save(outbox);
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("title", title);
        request.setAttribute("message", message);
        request.setAttribute("groupName", groupName);
        request.getRequestDispatcher("messageSent.jsp").forward(request, response);
    }
}
