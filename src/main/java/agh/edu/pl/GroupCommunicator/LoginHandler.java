package agh.edu.pl.GroupCommunicator;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import agh.edu.pl.GroupCommunicator.tables.User;


@WebServlet(name = "loginHandler", value = "/login-handler")
public class LoginHandler extends HttpServlet{
    public LoginHandler() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        if(email.isEmpty()){
            request.setAttribute("login_empty", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else{
            Session session = Main.getSession();
            User foundUser;
            try {
                Transaction tx = session.beginTransaction();
                List<User> usersList = session.createQuery("from User as user where user.email=:userEmail", User.class)
                        .setParameter("userEmail", email)
                        .getResultList();
                foundUser = usersList.isEmpty() ? null : usersList.get(0);
                tx.commit();
            } finally {
                session.close();
            }

            if (foundUser == null ){
                request.setAttribute("no_user", true);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                System.out.println(foundUser);
                request.setAttribute("firstname", foundUser.getFirstname());
                request.setAttribute("lastname", foundUser.getLastname());
                request.setAttribute("address", foundUser.getAddress());
                request.setAttribute("birthdate", foundUser.getBirthDate());
                request.setAttribute("email", email);
                request.getRequestDispatcher("/mainpage.jsp").forward(request, response);
            }
        }
    }
}