package agh.edu.pl.GroupCommunicator.tables;

import javax.persistence.*;

@Entity
@Table(name = "[Group]")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupID;
    @Column(nullable = false, unique = true, length = 32)
    private String name;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
