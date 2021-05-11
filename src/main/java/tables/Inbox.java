package tables;

import tables.pk.InboxPK;

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
    @MapsId("fromUser")
    @JoinColumn(name = "fromUser", nullable = false)
    private User fromUser;
    private boolean wasRead;

    public Inbox() {

    }

    public Inbox(Mail mail, User fromUser, boolean wasRead) {
        this.inboxId = new InboxPK(mail.getMailID(), fromUser.getUserID());
        this.mail = mail;
        this.fromUser = fromUser;
        this.wasRead = wasRead;
    }
}
