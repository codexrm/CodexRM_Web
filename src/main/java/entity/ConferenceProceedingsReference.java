package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "conferenceproceedingsreference")
public class ConferenceProceedingsReference extends Reference {

    @Column(name = "address")
    private String address;

    @Column(name = "series")
    private String series;

    @Column(name = "volume")
    private String volume;

    public ConferenceProceedingsReference() {}

    public ConferenceProceedingsReference(String author, String title, LocalDate date, String note, User user, String address, String series, String volume) {
        super(author, title, date, note, user);
        this.address = address;
        this.series = series;
        this.volume = volume;
    }

    public ConferenceProceedingsReference(int id, String author, LocalDate date, String note, String title, User user, String address, String series, String volume) {
        super(id, author, date, note, title, user);
        this.address = address;
        this.series = series;
        this.volume = volume;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getSeries() { return series; }

    public void setSeries(String series) { this.series = series; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }
}
