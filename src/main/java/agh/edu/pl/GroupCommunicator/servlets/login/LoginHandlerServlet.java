package agh.edu.pl.GroupCommunicator.servlets.login;

/*

    Redirects successfully logged user to his profile page.
    Redirects to mainPage.jsp on success and to index.jsp
    with error on failure

 */

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "LoginHandlerServlet", value = "/login-handler")
public class LoginHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = LoggedUser.getUser();

        if (currentUser == null) {
            request.setAttribute("email_first", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.setAttribute("user", currentUser);
            request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email.isEmpty()) {
            request.setAttribute("login_empty", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            Session session = HibernateUtils.getSession();
            User foundUser;

            try {
                Transaction tx = session.beginTransaction();

                List<User> usersList = session
                        .createQuery("from User as user where user.email=:userEmail", User.class)
                        .setParameter("userEmail", email)
                        .getResultList();
                foundUser = usersList.isEmpty() ? null : usersList.get(0);

                tx.commit();
            } finally {
                session.close();
            }

            if (foundUser == null) {
                request.setAttribute("no_user", true);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                LoggedUser.setUser(foundUser);
                request.setAttribute("user", foundUser);
                request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
            }
        }
    }
}