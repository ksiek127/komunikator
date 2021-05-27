package agh.edu.pl.GroupCommunicator.tables;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @ManyToOne
    @JoinColumn(name = "groupID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;

    public Mail() {

    }

    public Mail(String message, String title, Group group) {
        this.message = message;
        this.title = title;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public int getGroupId() {
        return this.group.getGroupID();
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return formatter.format(created);
    }
}
