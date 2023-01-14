package dto;

import java.time.LocalDate;
import entity.User;

public class ArticleReferenceDTO extends ReferenceDTO {

    private String journal;
    private String volume;
    private String number;
    private String pages;

    public ArticleReferenceDTO() {}

    public ArticleReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user, String journal, String volume, String number, String pages) {
        super(author, title, date, note, id, user);
        this.journal = journal;
        this.volume = volume;
        this.number = number;
        this.pages = pages;
    }

    public ArticleReferenceDTO(String author, String title, LocalDate date, String note, User user, String journal, String volume, String number, String pages) {
        super(author, title, date, note, user);
        this.journal = journal;
        this.volume = volume;
        this.number = number;
        this.pages = pages;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
