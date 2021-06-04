package agh.edu.pl.GroupCommunicator;

/*

    Deleted Account User stores necessary data from deleted accounts that is still used by the application
    This class is used to obtain User object of the Deleted Account User

 */

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
