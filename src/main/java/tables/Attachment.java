package tables;

import javax.persistence.*;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attachmentID;
    @ManyToOne
    @JoinColumn(name = "mailId", nullable = false)
    private Mail mail;
    @Column(nullable = false)
    private String attachmentPath;

    public Attachment() {

    }

    public Attachment(Mail mail, String attachmentPath) {
        this.mail = mail;
        this.attachmentPath = attachmentPath;
    }
}
