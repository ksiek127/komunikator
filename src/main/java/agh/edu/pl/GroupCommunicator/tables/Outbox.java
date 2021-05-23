package agh.edu.pl.GroupCommunicator.tables;

import agh.edu.pl.GroupCommunicator.tables.pk.OutboxPK;

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
    @MapsId("fromUser")
    @JoinColumn(name = "fromUser", nullable = false)
    private User fromUser;

    public Outbox() {

    }

    public Outbox(Mail mail, User fromUser) {
        this.outboxId = new OutboxPK(mail.getMailID(), fromUser.getUserID());
        this.mail = mail;
        this.fromUser = fromUser;
    }

}
