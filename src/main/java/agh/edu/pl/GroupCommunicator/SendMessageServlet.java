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

@WebServlet(name = "send-message", value = "/send-message")
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
            System.out.println("test3");
            System.out.println(request.getParameter("groupId"));
            int groupId = Integer.parseInt(request.getParameter("groupId"));
            System.out.println(groupId);
            Mail mail = new Mail(message, title, groupId);
            Inbox inbox = new Inbox(mail, user, false);
            List<User> members;
            try (Session session = Main.getSession()) {
                Transaction tx = session.beginTransaction();
                int userId = user.getUserID();
                members = session.createQuery("select user from GroupMember member where member.group.groupID = " + groupId, User.class)
                        .getResultList();
                for (User member : members) {
                    Outbox outbox = new Outbox(mail, member);
                    session.save(outbox);
                }
                session.save(mail);
                session.save(inbox);
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
