package agh.edu.pl.GroupCommunicator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.Address;
import tables.User;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "registrationHandler", value = "/registration-handler")
public class RegistrationHandler extends HttpServlet{
    public RegistrationHandler() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Date birthDate = Date.valueOf(request.getParameter("birthdate"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipcode");
        if(firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || street.isEmpty() || city.isEmpty() || zipCode.isEmpty()){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.include(request, response);
        }else{
            Address address1 = new Address(street, city, zipCode);
            User user = new User(birthDate, firstname, lastname, email, address1);
            Session session = Main.getSession();
            try {
                Transaction tx = session.beginTransaction();
                session.save(user);
                tx.commit();
            } finally {
                session.close();
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
