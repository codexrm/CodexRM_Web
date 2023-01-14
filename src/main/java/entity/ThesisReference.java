package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "thesisreference")
public class ThesisReference extends Reference {

    @Column(name = "address")
    private String address;

    @Column(name = "school")
    private String school;

    @Column(name = "type")
    private String type;

    public ThesisReference() {}

    public ThesisReference(String author, String title, LocalDate date, String note, User user, String address, String school, String type) {
        super(author, title, date, note, user);
        this.address = address;
        this.school = school;
        this.type = type;
    }

    public ThesisReference(int id, String author, LocalDate date, String note, String title, User user, String address, String school, String type) {
        super(id, author, date, note, title, user);
        this.address = address;
        this.school = school;
        this.type = type;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
