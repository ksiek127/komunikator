package agh.edu.pl.GroupCommunicator.tables;

import agh.edu.pl.GroupCommunicator.tables.pk.GroupRequestPK;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class GroupRequest implements Serializable {
    @EmbeddedId
    private GroupRequestPK groupRequestId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("groupId")
    @JoinColumn(name = "groupId", nullable = false)
    private Group group;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public int getUserId() {
        return this.groupRequestId.getUserId();
    }

    public int getGroupId() {
        return this.groupRequestId.getGroupId();
    }
}
