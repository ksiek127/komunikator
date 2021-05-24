package agh.edu.pl.GroupCommunicator.servlets.emails.groupbox;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Mail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GroupboxServlet", value = "/groupbox")
public class GroupboxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        List<Mail> emails = null;

        try (Session session = Main.getSession()) {
            Transaction tx = session.beginTransaction();
            emails = session.createQuery("select mail from Mail as mail where mail.groupId = " + groupId, Mail.class)
                    .getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("emails", emails);
        request.getRequestDispatcher("/groupmails.jsp").forward(request, response);
    }
}
