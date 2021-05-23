package agh.edu.pl.GroupCommunicator.tables;

import agh.edu.pl.GroupCommunicator.tables.pk.InboxPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Inbox implements Serializable {
    @EmbeddedId
    private InboxPK inboxId;

    @ManyToOne
    @MapsId("mailId")
    @JoinColumn(name = "mailId", nullable = false)
    private Mail mail;

    @ManyToOne
    @MapsId("toUser")
    @JoinColumn(name = "toUser", nullable = false)
    private User toUser;
    private boolean wasRead;

    public Inbox() {

    }

    public Inbox(Mail mail, User toUser, boolean wasRead) {
        this.inboxId = new InboxPK(mail.getMailID(), toUser.getUserID());
        this.mail = mail;
        this.toUser = toUser;
        this.wasRead = wasRead;
    }
}
