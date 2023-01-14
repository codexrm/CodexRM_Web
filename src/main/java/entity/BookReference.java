package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookreference")
@Inheritance(strategy = InheritanceType.JOINED)
public class BookReference extends Reference {

    @Column(name = "address")
    private String address;

    @Column(name = "edition")
    private String edition;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "series")
    private String series;

    @Column(name = "volume")
    private String volume;

    public BookReference() { }

    public BookReference(String author, String title, LocalDate date, String note, User user, String address, String edition, String publisher, String series, String volume) {
        super(author, title, date, note, user);
        this.address = address;
        this.edition = edition;
        this.publisher = publisher;
        this.series = series;
        this.volume = volume;
    }

    public BookReference(int id, String author, LocalDate date, String note, String title, User user, String address, String edition, String publisher, String series, String volume) {
        super(id, author, date, note, title, user);
        this.address = address;
        this.edition = edition;
        this.publisher = publisher;
        this.series = series;
        this.volume = volume;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getEdition() { return edition; }

    public void setEdition(String edition) { this.edition = edition; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getSeries() { return series; }

    public void setSeries(String series) { this.series = series; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }
}
