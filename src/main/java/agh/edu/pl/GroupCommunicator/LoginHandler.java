package agh.edu.pl.GroupCommunicator;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "loginHandler", value = "/login-handler")
public class LoginHandler extends HttpServlet{
    public LoginHandler() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        if(email.isEmpty()){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.include(request, response);
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginsuccess.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}