package agh.edu.pl.GroupCommunicator.servlets.groups;

/*

    Deletes group with a given id and redirects to a page provided as a request parameter (returnPage)
    with success or failure variable assigned to inform the user about the result

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

import java.io.IOException;

@WebServlet(name = "DeleteGroupServlet", urlPatterns = "/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("group_id"));
        String returnPage = request.getParameter("returnPage");

        if (returnPage == null) {
            returnPage = "/returnToMainPage";
        }

        Group group;
        Session session = HibernateUtils.getSession();
        try {
            Transaction tx = session.beginTransaction();

            group = session.get(Group.class, groupId);

            session.delete(group);

            tx.commit();
        } catch (Exception ex) {
            request.setAttribute("group_remove_failed", true);
            request.getRequestDispatcher(returnPage).forward(request, response);
            ex.printStackTrace();
        } finally {
            session.close();
        }

        request.setAttribute("group_remove_success", true);
        request.getRequestDispatcher(returnPage).forward(request, response);
    }
}
