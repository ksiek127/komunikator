package agh.edu.pl.GroupCommunicator.servlets.mails;

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
import org.hibernate.query.Query;

import java.io.IOException;

@WebServlet(name = "DeleteMessage", value = "/delete-message")
public class DeleteMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mailId = Integer.parseInt(request.getParameter("mailId"));
        try (Session session = Main.getSession()) {
            User user = Main.getUser();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete from Inbox as inbox where inbox.mail.mailID = " + mailId
            + " and inbox.toUser.userID = " + user.getUserID());
            int result = query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String where = request.getParameter("where");
//        if(where.equals("inbox")){
//            request.getRequestDispatcher("/inbox").forward(request, response);
//        }else if(where.equals("outbox")){
//            request.getRequestDispatcher("/outbox").forward(request, response);
//        }
        request.getRequestDispatcher("/mainpage.jsp").forward(request, response);
    }
}
