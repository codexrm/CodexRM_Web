package entity;

import javax.persistence.*;

@Entity
@Table(name = "reference")
@Inheritance(strategy = InheritanceType.JOINED)
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;


    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "note")
    private String note;

    public Reference() {}

    public Reference(String title, String year, String month, String note) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
    }

    public Reference(Integer id, String title, String year, String month, String note) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}