package dto;

import entity.User;

import java.time.LocalDate;

public class BookLetReferenceDTO extends ReferenceDTO{

    private String howpublished;
    private String address;

    public BookLetReferenceDTO() {}

    public BookLetReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user, String howpublished, String address) {
        super(author, title, date, note, id, user);
        this.howpublished = howpublished;
        this.address = address;
    }

    public BookLetReferenceDTO(String author, String title, LocalDate date, String note, User user, String howpublished, String address) {
        super(author, title, date, note, user);
        this.howpublished = howpublished;
        this.address = address;
    }

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {this.howpublished = howpublished;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
