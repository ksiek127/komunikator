package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Mail;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@SuppressWarnings("unchecked")
@WebServlet(name = "editdataform", value = "/editdataform")
public class EditDataForm extends HttpServlet {

    public EditDataForm() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        request.setAttribute("email", email);
        request.getRequestDispatcher("/editdata.jsp").forward(request, response);
    }

}
