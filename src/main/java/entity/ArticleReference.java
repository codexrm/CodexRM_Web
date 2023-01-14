package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "articlereference")
public class ArticleReference extends Reference {

    @Column(name = "journal")
    private String journal;

    @Column(name = "numbera")
    private String number;

    @Column(name = "pages")
    private String pages;

    @Column(name = "volume")
    private String volume;

    public ArticleReference() { }

    public ArticleReference(String author, String title, LocalDate date, String note, User user, String journal, String number, String pages, String volume) {
        super(author, title, date, note, user);
        this.journal = journal;
        this.number = number;
        this.pages = pages;
        this.volume = volume;
    }

    public String getJournal() { return journal; }

    public ArticleReference(int id, String author, LocalDate date, String note, String title, User user, String journal, String number, String pages, String volume) {
        super(id, author, date, note, title, user);
        this.journal = journal;
        this.number = number;
        this.pages = pages;
        this.volume = volume;
    }

    public void setJournal(String journal) { this.journal = journal; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }
}
