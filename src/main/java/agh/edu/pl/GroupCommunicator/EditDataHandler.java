package agh.edu.pl.GroupCommunicator;

import java.io.IOException;
import java.sql.Date;


import agh.edu.pl.GroupCommunicator.tables.Address;
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

@WebServlet(name = "editDataHandler", value = "/edit-data-handler")
public class EditDataHandler extends HttpServlet{
    public EditDataHandler() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipcode");

        User user = null;


        if (firstname.isEmpty() && lastname.isEmpty() && email.isEmpty() && street.isEmpty()
                && city.isEmpty() && zipCode.isEmpty() && request.getParameter("birthdate").isEmpty()){
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/editdata.jsp").forward(request, response);
        } else {

            Session session = Main.getSession();
            try {
                Transaction tx = session.beginTransaction();

                Criteria criteria = session.createCriteria(User.class);
                user = (User) criteria.add(Restrictions.eq("email", request.getParameter("oldemail"))).uniqueResult();

                if (!firstname.isEmpty()) {
                    user.setFirstname(firstname);
                }

                if (!lastname.isEmpty()) {
                    user.setLastname(lastname);
                }

                if (!email.isEmpty()) {
                    user.setEmail(email);
                }

                if (!(street.isEmpty() && city.isEmpty() && zipCode.isEmpty())) {
                    Address address = user.getAddress();

                    if (!street.isEmpty()) {
                        address.setStreet(street);
                    }

                    if (!city.isEmpty()) {
                        address.setCity(city);
                    }

                    if (!zipCode.isEmpty()) {
                        address.setZipCode(zipCode);
                    }

                    user.setAddress(address);

                }

                if (!request.getParameter("birthdate").isEmpty()){
                    Date birthDate = Date.valueOf(request.getParameter("birthdate"));
                    user.setBirthDate(birthDate);
                }

                tx.commit();
            } catch (ConstraintViolationException ex) {
                request.setAttribute("constraint_exception", true);
                request.getRequestDispatcher("/editdata.jsp").forward(request, response);
            } finally {
                session.close();
            }
            request.setAttribute("edit_data_successful", true);
            request.setAttribute("firstname", user.getFirstname());
            request.setAttribute("lastname", user.getLastname());
            request.setAttribute("address", user.getAddress());
            request.setAttribute("birthdate", user.getBirthDate());
            request.setAttribute("email", user.getEmail());
            request.getRequestDispatcher("/mainpage.jsp").forward(request, response);
        }
    }
}
