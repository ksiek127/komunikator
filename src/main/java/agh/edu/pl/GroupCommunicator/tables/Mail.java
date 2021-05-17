package agh.edu.pl.GroupCommunicator.tables;

import javax.persistence.*;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mailID;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private String title;

    public Mail() {

    }

    public Mail(String message, String title) {
        this.message = message;
        this.title = title;
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
}
