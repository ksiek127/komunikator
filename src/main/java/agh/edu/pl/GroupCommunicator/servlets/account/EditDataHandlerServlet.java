package agh.edu.pl.GroupCommunicator.servlets.account;

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.LoggedUser;
import agh.edu.pl.GroupCommunicator.tables.Address;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.sql.Date;

/*

    When user wants to edit their data, this servlet checks if new data doesn't violate constraints,
    and if it's legal, user's data is updated.

 */

@WebServlet(name = "EditDataHandlerServlet", value = "/edit-data-handler")
public class EditDataHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String zipCode = request.getParameter("zipcode");

        User user = LoggedUser.getUser();

        if (user == null) {
            request.setAttribute("email_first", true);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        String oldZipCode = user.getAddress().getZipCode();
        String oldEmail = user.getEmail();

        if (firstname.isEmpty() && lastname.isEmpty() && email.isEmpty() && street.isEmpty()
                && city.isEmpty() && zipCode.isEmpty() && country.isEmpty() &&
                request.getParameter("birthdate").isEmpty()) {
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/editData.jsp").forward(request, response);
        } else {

            Session session = HibernateUtils.getSession();
            try {
                Transaction tx = session.beginTransaction();

                if (!firstname.isEmpty()) {
                    user.setFirstname(firstname);
                }

                if (!lastname.isEmpty()) {
                    user.setLastname(lastname);
                }

                if (!email.isEmpty()) {
                    user.setEmail(email);
                }

                if (!(street.isEmpty() && city.isEmpty() && zipCode.isEmpty() && country.isEmpty())) {
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

                    if (!country.isEmpty()) {
                        address.setCountry(country);
                    }

                    user.setAddress(address);

                }

                if (!request.getParameter("birthdate").isEmpty()) {
                    Date birthDate = Date.valueOf(request.getParameter("birthdate"));
                    user.setBirthDate(birthDate);
                }

                session.update(user);
                tx.commit();
            } catch (PersistenceException ex) {
                user.getAddress().setZipCode(oldZipCode);
                user.setEmail(oldEmail);
                request.setAttribute("constraint_exception", true);
                request.getRequestDispatcher("/editData.jsp").forward(request, response);
            } finally {
                session.close();
            }
            request.setAttribute("edit_data_successful", true);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
        }
    }
}