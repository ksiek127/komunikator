import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandler extends HttpServlet{
    public LoginHandler() {
        super();
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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