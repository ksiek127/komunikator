package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "RemoveUserServlet", value = "/removeUser")
public class RemoveUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = Main.getUser();

        Transaction tx = null;
        Session session = Main.getSession();
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            request.setAttribute("error_while_removing_user", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        request.setAttribute("removed_user", true);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
