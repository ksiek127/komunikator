package agh.edu.pl.GroupCommunicator.servlets.groups;

/*

    Updates group data and redirects to GroupsServlet on success or to editGroupData.jsp with a failure variable
    assigned

 */

import agh.edu.pl.GroupCommunicator.HibernateUtils;
import agh.edu.pl.GroupCommunicator.tables.Group;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.io.IOException;

@WebServlet(name = "EditGroupDataHandlerServlet", value = "/edit-group-data-handler")
public class EditGroupDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        int groupId = Integer.parseInt(request.getParameter("groupId")); // twierdzi ze pusty string

        Group group = null;
        String oldname = null;

        if (name.isEmpty() && description.isEmpty()) {
            request.setAttribute("empty_fields", true);
            request.getRequestDispatcher("/editGroupData.jsp").forward(request, response);
        } else {

            Session session = HibernateUtils.getSession();
            try {
                Transaction tx = session.beginTransaction();

                group = session.createQuery("from Group as group where group.groupID = " + groupId, Group.class)
                        .getResultList().get(0);

                oldname = group.getName();

                if (!name.isEmpty()) {
                    group.setName(name);
                }

                if (!description.isEmpty()) {
                    group.setDescription(description);
                }

                session.update(group);
                tx.commit();
            } catch (PersistenceException ex) {
                assert group != null;
                group.setName(oldname);
                request.setAttribute("constraint_exception", true);
                request.getRequestDispatcher("/editGroupData.jsp").forward(request, response);
            } finally {
                session.close();
            }
            request.setAttribute("edit_data_successful", true);
            request.getRequestDispatcher("/groups").forward(request, response);
        }
    }
}
