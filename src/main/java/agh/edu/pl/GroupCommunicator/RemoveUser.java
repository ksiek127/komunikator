package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Mail;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unchecked")
@WebServlet(name = "removeUser", value = "/removeUser")
public class RemoveUser extends HttpServlet {

    public RemoveUser() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        User user = null;

        Transaction tx = null;
        Session session = Main.getSession();
        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();

            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        request.setAttribute("removed_user", true);
        request.setAttribute("user_email", email);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
