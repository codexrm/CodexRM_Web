package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "conferencepaperreference")
public class ConferencePaperReference extends Reference {

    @Column(name = "address")
    private String address;

    @Column(name = "pages")
    private String pages;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "volume")
    private String volume;

    public ConferencePaperReference() {}

    public ConferencePaperReference(String author, String title, LocalDate date, String note, User user, String address, String pages, String publisher, String volume) {
        super(author, title, date, note, user);
        this.address = address;
        this.pages = pages;
        this.publisher = publisher;
        this.volume = volume;
    }

    public ConferencePaperReference(int id, String author, LocalDate date, String note, String title, User user, String address, String pages, String publisher, String volume) {
        super(id, author, date, note, title, user);
        this.address = address;
        this.pages = pages;
        this.publisher = publisher;
        this.volume = volume;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }
}
