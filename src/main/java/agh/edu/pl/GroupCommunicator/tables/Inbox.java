package agh.edu.pl.GroupCommunicator.tables;

import agh.edu.pl.GroupCommunicator.tables.pk.InboxPK;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Inbox implements Serializable {
    @EmbeddedId
    private InboxPK inboxId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("mailId")
    @JoinColumn(name = "mailId", nullable = false)
    private Mail mail;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("toUser")
    @JoinColumn(name = "toUser", nullable = false)
    private User toUser;
    private boolean wasRead;

    public Inbox() {

    }

    public Inbox(Mail mail, User toUser) {
        this.inboxId = new InboxPK(mail.getMailID(), toUser.getUserID());
        this.mail = mail;
        this.toUser = toUser;
        this.wasRead = false;
    }

    public Mail getMail() {
        return mail;
    }

    public User getToUser() {
        return toUser;
    }

    public boolean getWasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }
}
