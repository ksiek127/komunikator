package agh.edu.pl.GroupCommunicator.tables;

import agh.edu.pl.GroupCommunicator.tables.pk.GroupMemberPK;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Entity
@Check(constraints = "groupRank in ('Admin', 'Member', 'Moderator')")
public class GroupMember {
    @EmbeddedId
    private GroupMemberPK groupMemberId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "groupId", nullable = false)
    private Group group;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupRank groupRank;

    public GroupMember() {

    }

    public GroupMember(User user, Group group, GroupRank groupRank) {
        this.groupMemberId = new GroupMemberPK(user.getUserID(), group.getGroupID());
        this.user = user;
        this.group = group;
        this.groupRank = groupRank;
    }

    public int getUserId() {
        return this.groupMemberId.getUserId();
    }

    public int getGroupId() {
        return this.groupMemberId.getGroupId();
    }
}
