package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupMember;
import agh.edu.pl.GroupCommunicator.tables.GroupRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GroupsSearchServlet", urlPatterns = "/groups-search")
public class GroupsSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("im here get");
        request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String groupNameRegex = request.getParameter("group_name");
        if (groupNameRegex == null) {
            request.setAttribute("no_group_name", true);
            request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
        } else {
            Session session = Main.getSession();
            List<Group> groupsList = null;
            Map<Group, String> groupsMap = new HashMap<>();
            try {
                Transaction tx = session.beginTransaction();

                groupsList = session
                        .createQuery("from Group as group where group.name like :groupNameRegex", Group.class)
                        .setParameter("groupNameRegex", '%' + groupNameRegex + '%')
                        .getResultList();

                for (Group group: groupsList) {
//                    check if use is already in the group
                    List<GroupMember> gm = session
                            .createQuery("from GroupMember as gm where gm.group.groupID=:gId and gm.user.userID=:uId",
                                    GroupMember.class)
                            .setParameter("gId", group.getGroupID())
                            .setParameter("uId", Main.getUser().getUserID())
                            .getResultList();
                    if (gm != null && gm.size() == 1) {
                        groupsMap.put(group, "joined");
                    } else {
//                        check if user already requested joining the group
                        List<GroupRequest> gr = session
                                .createQuery("from GroupRequest as gr where gr.group.groupID=:gId and gr.user.userID=:uId",
                                        GroupRequest.class)
                                .setParameter("gId", group.getGroupID())
                                .setParameter("uId", Main.getUser().getUserID())
                                .getResultList();
                        if (gr != null && gr.size() == 1) {
                            groupsMap.put(group, "requested");
                        } else {
                            groupsMap.put(group, "none");
                        }
                    }
                }
                tx.commit();
            } catch (Exception ex) {
                request.setAttribute("search_error", true);
                request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
                ex.printStackTrace();
            } finally {
                session.close();
            }

            if (groupsList == null || groupsList.size() == 0) {
                request.setAttribute("no_such_group", true);
            }
            request.setAttribute("groups", groupsMap);
            request.getRequestDispatcher("searchgroup.jsp").forward(request, response);
        }

    }

}
