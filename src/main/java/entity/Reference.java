package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reference")
@Inheritance(strategy = InheritanceType.JOINED)
public class Reference {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "dater")
    private LocalDate date;

    @Column(name = "note")
    private String note;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usercodex")
    private User user;

    public Reference() {
    }

    public Reference(String author, String title, LocalDate date, String note, User user) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.note = note;
        this.user = user;
    }

    public Reference(int id, String author, LocalDate date, String note, String title, User user) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.note = note;
        this.title = title;
        this.user = user;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public String getNote() {return note;}

    public void setNote(String note) {this.note = note;}

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
