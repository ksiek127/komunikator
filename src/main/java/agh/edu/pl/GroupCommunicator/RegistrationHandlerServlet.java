package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Address;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "RegistrationHandlerServlet", value = "/registration-handler")
public class RegistrationHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipcode");
        String country = request.getParameter("country");

        if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || street.isEmpty()
                || city.isEmpty() || zipCode.isEmpty() || country.isEmpty() ||
                request.getParameter("birthdate").isEmpty()) {
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            Date birthDate = Date.valueOf(request.getParameter("birthdate"));
            Address address1 = new Address(street, city, zipCode, country);
            User user = new User(birthDate, firstname, lastname, email, address1);
            Session session = Main.getSession();
            try {
                Transaction tx = session.beginTransaction();
                session.save(user);
                tx.commit();
            } catch (ConstraintViolationException ex) {
                request.setAttribute("constraint_exception", true);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            } finally {
                session.close();
            }
            request.setAttribute("created_user", true);
            request.setAttribute("user_email", email);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
