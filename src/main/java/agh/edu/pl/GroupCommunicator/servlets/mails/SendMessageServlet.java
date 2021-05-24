package agh.edu.pl.GroupCommunicator.servlets.mails;

import agh.edu.pl.GroupCommunicator.Main;
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
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String groupName = null;
        if(title.isEmpty() || message.isEmpty()){
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/sendmessage.jsp").forward(request, response);
        }else{
            List<GroupMember> members;
            try (Session session = Main.getSession()) {
                Transaction tx = session.beginTransaction();

                groupName = session
                        .createQuery("from Group as group where group.groupID=:groupId", Group.class)
                        .setParameter("groupId", groupId)
                        .getResultList().get(0).getName();

                Mail mail = new Mail(message, title, groupId);
                Outbox outbox = new Outbox(mail, user);
                session.save(mail);
                members = session
                        .createQuery("from GroupMember as gm where gm.group.groupID=:gId", GroupMember.class)
                        .setParameter("gId", groupId)
                        .getResultList();
                System.out.println(members);
                for (GroupMember member : members) {
                    if (member.getUser().getUserID() != Main.getUser().getUserID()) {
                        System.out.println(member.getUser().getUserID());
                        Inbox inbox = new Inbox(mail, member.getUser(), false);
                        session.save(inbox);
                    }
                }
                session.save(outbox);
                tx.commit();
            } catch (Exception e) {
//                tu zrobic poinformowanie uzytkownika o bledzie
                e.printStackTrace();
            }
        }
        //response.sendRedirect("messagesent.jsp");
        request.setAttribute("title", title);
        request.setAttribute("message", message);
        request.setAttribute("groupName", groupName);
        request.getRequestDispatcher("messagesent.jsp").forward(request, response);
    }
}
