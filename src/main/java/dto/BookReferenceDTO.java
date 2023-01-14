package dto;

import entity.User;

import java.time.LocalDate;

public class BookReferenceDTO extends ReferenceDTO{

    protected String publisher;
    protected String volume;
    protected String series;
    protected String address;
    protected String edition;

    public BookReferenceDTO() {}

    public BookReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user, String publisher, String volume, String series, String address, String edition) {
        super(author, title, date, note, id, user);
        this.publisher = publisher;
        this.volume = volume;
        this.series = series;
        this.address = address;
        this.edition = edition;
    }

    public BookReferenceDTO(String author, String title, LocalDate date, String note, User user, String publisher, String volume, String series, String address, String edition) {
        super(author, title, date, note, user);
        this.publisher = publisher;
        this.volume = volume;
        this.series = series;
        this.address = address;
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
