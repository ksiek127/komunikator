package agh.edu.pl.GroupCommunicator;

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
            throws ServletException, IOException{
        User user = Main.getUser();
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        if(title.isEmpty() || message.isEmpty()){
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/sendmessage.jsp").forward(request, response);
        }else{
            int groupId = Integer.parseInt(request.getParameter("groupId"));
            List<GroupMember> members;
            try (Session session = Main.getSession()) {
                Transaction tx = session.beginTransaction();
                Mail mail = new Mail(message, title, groupId);
                Inbox inbox = new Inbox(mail, user, false);
                session.save(mail);
                members = session
                        .createQuery("from GroupMember as gm where gm.group.groupID=:gId", GroupMember.class)
                        .setParameter("gId", groupId)
                        .getResultList();
                for (GroupMember member : members) {
                    Outbox outbox = new Outbox(mail, member.getUser());
                    session.save(outbox);
                }
                session.save(inbox);
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("messagesent.jsp");
    }
}
