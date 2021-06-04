package agh.edu.pl.GroupCommunicator.servlets.account;

/*

    Forwards email to panel where user can edit their data.

 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        request.getRequestDispatcher("/editData.jsp").forward(request, response);
    }

}
