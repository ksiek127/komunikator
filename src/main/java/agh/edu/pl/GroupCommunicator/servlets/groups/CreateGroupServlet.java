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
import org.hibernate.query.Query;

import java.io.IOException;

@WebServlet(name = "CreateGroupServlet", value = "/create-group")
public class CreateGroupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        User user = Main.getUser();
        if (name.isEmpty() || description.isEmpty()) {
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/createGroup.jsp").forward(request, response);
        }
        Long count;
        try (Session session = Main.getSession()) {
            Query query = session.createQuery("select count(*) from Group group where group.name = :gName");
            query.setParameter("gName", name);
            count = (Long) query.uniqueResult();
        }
        if (count > 0) {
            request.setAttribute("name_taken", true);
            request.getRequestDispatcher("/createGroup.jsp").forward(request, response);
        } else {
            Group group = new Group(name, description);
            try (Session session = Main.getSession()) {
                Transaction tx = session.beginTransaction();
                session.save(group);
                GroupMember groupMember = new GroupMember(user, group, GroupRank.ADMIN);
                session.save(groupMember);
                tx.commit();
            } catch (ConstraintViolationException ex) {
                request.setAttribute("constraint_exception", true);
                request.getRequestDispatcher("/createGroup.jsp").forward(request, response);
            }
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.getRequestDispatcher("groupCreated.jsp").forward(request, response);
        }
    }
}
