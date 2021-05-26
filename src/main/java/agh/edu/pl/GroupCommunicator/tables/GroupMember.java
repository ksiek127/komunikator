package agh.edu.pl.GroupCommunicator.tables;

import agh.edu.pl.GroupCommunicator.tables.pk.GroupMemberPK;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Check(constraints = "groupRank in ('Admin', 'Member', 'Moderator')")
public class GroupMember {
    @EmbeddedId
    private GroupMemberPK groupMemberId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapsId("userId")
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public GroupRank getGroupRank() {
        return groupRank;
    }

    public void setGroupRank(GroupRank groupRank) {
        this.groupRank = groupRank;
    }

    public User getUser() {
        return user;
    }
}
