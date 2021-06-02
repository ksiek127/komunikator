package agh.edu.pl.GroupCommunicator;

import agh.edu.pl.GroupCommunicator.tables.User;

public class LoggedUser {
    private static User user;

    public static void setUser(User user) {
        LoggedUser.user = user;
    }

    public static User getUser() {
        return user;
    }
}
