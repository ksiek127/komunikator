package agh.edu.pl.GroupCommunicator.servlets.mails;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "MessageServlet", value = "/message-servlet")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String msg = request.getParameter("msg");
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String groupName;
        Session session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();
            Group group = session.createQuery("from Group as group where group.groupID = " + groupId, Group.class)
                    .getResultList().get(0);
            groupName = group.getName();
            tx.commit();
        } finally {
            session.close();
        }
        request.setAttribute("title", title);
        request.setAttribute("msg", msg);
        request.setAttribute("groupName", groupName);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
