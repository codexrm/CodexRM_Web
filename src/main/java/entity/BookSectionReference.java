package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booksectionreference")
public class BookSectionReference extends BookReference {

    @Column(name = "chapter")
    private String chapter;

    @Column(name = "pages")
    private String pages;

    public BookSectionReference() {}

    public BookSectionReference(String author, String title, LocalDate date, String note, User user, String address, String edition, String publisher, String series, String volume, String chapter, String pages) {
        super(author, title, date, note, user, address, edition, publisher, series, volume);
        this.chapter = chapter;
        this.pages = pages;
    }

    public BookSectionReference(int id, String author, LocalDate date, String note, String title, User user, String address, String edition, String publisher, String series, String volume, String chapter, String pages) {
        super(id, author, date, note, title, user, address, edition, publisher, series, volume);
        this.chapter = chapter;
        this.pages = pages;
    }

    public String getChapter() { return chapter; }

    public void setChapter(String chapter) { this.chapter = chapter; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }
}
