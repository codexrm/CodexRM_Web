package dto;

import entity.User;

import java.time.LocalDate;

public class ConferencePaperReferenceDTO extends ReferenceDTO{

    private String publisher;
    private String volume;
    private String address;
    private String pages;

    public ConferencePaperReferenceDTO() {}

    public ConferencePaperReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user, String publisher, String volume, String address, String pages) {
        super(author, title, date, note, id, user);
        this.publisher = publisher;
        this.volume = volume;
        this.address = address;
        this.pages = pages;
    }

    public ConferencePaperReferenceDTO(String author, String title, LocalDate date, String note, User user, String publisher, String volume, String address, String pages) {
        super(author, title, date, note, user);
        this.publisher = publisher;
        this.volume = volume;
        this.address = address;
        this.pages = pages;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }
}
