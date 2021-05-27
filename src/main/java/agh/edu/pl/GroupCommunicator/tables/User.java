package agh.edu.pl.GroupCommunicator.tables;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "[User]")
@Check(constraints = "birthDate < CURRENT_TIMESTAMP and email like '%@%' and zipCode like '[0-9][0-9]-[0-9][0-9][0-9]'")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @Embedded
    private Address address;

    public User() {

    }

    public User(Date birthDate, String firstname, String lastname, String email, Address address) {
        this.birthDate = birthDate;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
    }

    public String getNameAndEmailAndAddress() {
        return "User name: " + this.firstname + " " + this.lastname + "     Email: " + this.email +
                "     Address: " + this.address.toString();
    }

    public String getNameAndEmail() {
        return "User name: " + this.firstname + " " + this.lastname + "     Email: " + this.email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBirthDate() {
        return birthDate.toString();
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
