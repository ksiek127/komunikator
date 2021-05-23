package agh.edu.pl.GroupCommunicator.tables.pk;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OutboxPK implements Serializable {
    private int mailId;
    private int fromUser;

    public OutboxPK() {
    }

    public OutboxPK(int mailId, int fromUser) {
        this.mailId = mailId;
        this.fromUser = fromUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutboxPK outboxPK = (OutboxPK) o;
        return mailId == outboxPK.mailId && fromUser == outboxPK.fromUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailId, fromUser);
    }
}
