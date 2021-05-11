package tables.pk;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InboxPK implements Serializable {
    private int mailId;
    private int fromUser;

    public InboxPK() {
    }

    public InboxPK(int mailId, int fromUser) {
        this.mailId = mailId;
        this.fromUser = fromUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InboxPK inboxPK = (InboxPK) o;
        return mailId == inboxPK.mailId && fromUser == inboxPK.fromUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailId, fromUser);
    }
}
