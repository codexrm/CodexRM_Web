package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookletreference")
public class BookLetReference extends Reference{

    @Column(name = "address")
    private String address;

    @Column(name = "howpublished")
    private String howpublished;

    public BookLetReference() { }

    public BookLetReference(String author, String title, LocalDate date, String note, User user, String address, String howpublished) {
        super(author, title, date, note, user);
        this.address = address;
        this.howpublished = howpublished;
    }

    public BookLetReference(int id, String author, LocalDate date, String note, String title, User user, String address, String howpublished) {
        super(id, author, date, note, title, user);
        this.address = address;
        this.howpublished = howpublished;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getHowpublished() { return howpublished; }

    public void setHowpublished(String howpublished) { this.howpublished = howpublished; }
}
