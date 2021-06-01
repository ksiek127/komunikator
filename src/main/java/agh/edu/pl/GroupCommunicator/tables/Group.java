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
    @Column(nullable = false, length = 100)
    private String description;

    public Group() {
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getNameToLower() {
        return name.toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
    }
}
