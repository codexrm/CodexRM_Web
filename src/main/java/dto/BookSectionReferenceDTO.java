package dto;

import entity.User;

import java.time.LocalDate;

public class BookSectionReferenceDTO extends  BookReferenceDTO{

    private String chapter;
    private String pages;

    public BookSectionReferenceDTO() {}

    public BookSectionReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user, String publisher, String volume, String series, String address, String edition, String chapter, String pages) {
        super(author, title, date, note, id, user, publisher, volume, series, address, edition);
        this.chapter = chapter;
        this.pages = pages;
    }

    public BookSectionReferenceDTO(String author, String title, LocalDate date, String note, User user, String publisher, String volume, String series, String address, String edition, String chapter, String pages) {
        super(author, title, date, note, user, publisher, volume, series, address, edition);
        this.chapter = chapter;
        this.pages = pages;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
