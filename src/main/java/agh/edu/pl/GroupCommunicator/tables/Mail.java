package agh.edu.pl.GroupCommunicator.tables;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mailID;
    @Column(nullable = false, length = 1000)
    private String message;
    @Column(nullable = false, length = 32)
    private String title;
    @Column(nullable = false)
    private Date created;
    @Column
    private int groupId;

    public Mail() {

    }

    public Mail(String message, String title, int groupId) {
        this.message = message;
        this.title = title;
        this.groupId = groupId;
        this.created = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public int getMailID() {
        return mailID;
    }

    public void setMailID(int mailID) {
        this.mailID = mailID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
