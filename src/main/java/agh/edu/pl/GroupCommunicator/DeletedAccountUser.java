package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.User;
import org.hibernate.Session;

public class DeletedAccountUser {
    public static User getDeletedAccountUser(Session session) {
        return session
                .createQuery("from User as u where u.email=:deletedUserEmail", User.class)
                .setParameter("deletedUserEmail", "deleted@account")
                .uniqueResult();
    }
}
