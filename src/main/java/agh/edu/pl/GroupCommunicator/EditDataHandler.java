package agh.edu.pl.GroupCommunicator;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "editDataHandler", value = "/edit-data-handler")
public class EditDataHandler {
    public EditDataHandler() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Date birthDate = Date.valueOf(request.getParameter("birthdate"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        if(firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || address.isEmpty()){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("editdata.jsp");
            requestDispatcher.include(request, response);
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("mainpage.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
