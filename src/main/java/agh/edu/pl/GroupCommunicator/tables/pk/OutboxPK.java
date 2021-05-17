package agh.edu.pl.GroupCommunicator.tables.pk;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OutboxPK implements Serializable {
    private int mailId;
    private int toUser;

    public OutboxPK() {
    }

    public OutboxPK(int mailId, int toUser) {
        this.mailId = mailId;
        this.toUser = toUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutboxPK outboxPK = (OutboxPK) o;
        return mailId == outboxPK.mailId && toUser == outboxPK.toUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailId, toUser);
    }
}
