package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.Group;
import agh.edu.pl.GroupCommunicator.tables.GroupRank;
import agh.edu.pl.GroupCommunicator.tables.GroupRequest;
import agh.edu.pl.GroupCommunicator.tables.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "group-requests-servlet", urlPatterns = "/group-requests-servlet")
public class GroupRequestsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GroupRequest> requests;
        Session session = Main.getSession();
        try {
            Transaction tx = session.beginTransaction();
            requests = session.createQuery("from GroupRequest as gr join GroupMember as gm on gr.group.groupID = gm.group.groupID where gm.groupRank = " + GroupRank.ADMIN
            + " and gm.user.userID = " + Main.getUser().getUserID(), GroupRequest.class)
                    .getResultList();
            tx.commit();
        } finally {
            session.close();
        }
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> groupNames = new ArrayList<>();
        for(int i=0; i<requests.size(); i++){
            Session s = Main.getSession();
            try{
                Transaction t = s.beginTransaction();
                String username = s.createQuery("from User as user where user.userID = " + requests.get(i).getUserId(), User.class)
                        .getResultList().get(0).getFirstname() + " " + s.createQuery("from User as user where user.userID = " + requests.get(i).getUserId(), User.class)
                        .getResultList().get(0).getLastname();
                String groupName = s.createQuery("from Group as group where group.groupID = " + requests.get(i).getGroupId(), Group.class)
                        .getResultList().get(0).getName();
                usernames.add(username);
                groupNames.add(groupName);
                t.commit();
            }finally {
                s.close();
            }
        }
        request.setAttribute("requests", requests);
        request.setAttribute("usernames", usernames);
        request.setAttribute("groupNames", groupNames);
        request.getRequestDispatcher("/grouprequests.jsp").forward(request, response);
    }
}
