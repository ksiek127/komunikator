package tables;

import tables.pk.GroupRequestPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GroupRequest implements Serializable {
    @EmbeddedId
    private GroupRequestPK groupRequestId;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "groupId", nullable = false)
    private Group group;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public GroupRequest() {
    }

    public GroupRequest(Group group, User user) {
        this.groupRequestId = new GroupRequestPK(user.getUserID(), group.getGroupID());
        this.group = group;
        this.user = user;
    }
}
