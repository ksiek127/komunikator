package tables;

import tables.pk.OutboxPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Outbox implements Serializable {
    @EmbeddedId
    private OutboxPK outboxId;

    @ManyToOne
    @MapsId("mailId")
    @JoinColumn(name = "mailId", nullable = false)
    private Mail mail;

    @ManyToOne
    @MapsId("toUser")
    @JoinColumn(name = "toUser", nullable = false)
    private User toUser;

    public Outbox() {

    }

    public Outbox(Mail mail, User toUser) {
        this.outboxId = new OutboxPK(mail.getMailID(), toUser.getUserID());
        this.mail = mail;
        this.toUser = toUser;
    }

}
