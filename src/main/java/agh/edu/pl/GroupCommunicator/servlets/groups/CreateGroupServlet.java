package agh.edu.pl.GroupCommunicator.servlets.groups;

import agh.edu.pl.GroupCommunicator.Main;
import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.GroupRank;
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
import java.util.List;

@WebServlet(name = "CreateGroupServlet", value = "/create-group")
public class CreateGroupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String email = request.getParameter("email");
        User user = Main.getUser();
        if(name.isEmpty() || description.isEmpty()){
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/creategroup.jsp").forward(request, response);
        }else{
            Group group = new Group(name, description);
            Session session = Main.getSession();
            try {
                Transaction tx = session.beginTransaction();
                session.save(group);
                GroupMember groupMember = new GroupMember(user, group, GroupRank.ADMIN);
                session.save(groupMember);
                tx.commit();
            } catch (ConstraintViolationException ex) {
                request.setAttribute("constraint_exception", true);
                request.getRequestDispatcher("/creategroup.jsp").forward(request, response);
            } finally {
                session.close();
            }
            request.getRequestDispatcher("/returnToMainPage").forward(request, response);
        }
    }
}
