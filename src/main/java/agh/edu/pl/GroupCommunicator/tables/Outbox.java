package agh.edu.pl.GroupCommunicator.tables;

import agh.edu.pl.GroupCommunicator.tables.pk.OutboxPK;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Outbox implements Serializable {
    @EmbeddedId
    private OutboxPK outboxId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("mailId")
    @JoinColumn(name = "mailId", nullable = false)
    private Mail mail;

    @ManyToOne
    @MapsId("fromUser")
    @JoinColumn(name = "fromUser", nullable = false)
    private User fromUser;

    private boolean wasDeleted;


    public Outbox() {

    }

    public Outbox(Mail mail, User fromUser) {
        this.outboxId = new OutboxPK(mail.getMailID(), fromUser.getUserID());
        this.mail = mail;
        this.fromUser = fromUser;
        this.wasDeleted = false;
    }

    public Mail getMail() {
        return mail;
    }

    public User getFromUser() {
        return fromUser;
    }

    public boolean getWasDeleted() {
        return wasDeleted;
    }

    public void setWasDeleted(boolean wasDeleted) {
        this.wasDeleted = wasDeleted;
    }
}
