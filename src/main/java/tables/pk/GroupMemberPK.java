package tables.pk;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GroupMemberPK implements Serializable {
    private int userId;
    private int groupId;

    public GroupMemberPK() {

    }

    public GroupMemberPK(int userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberPK that = (GroupMemberPK) o;
        return userId == that.userId && groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
}
