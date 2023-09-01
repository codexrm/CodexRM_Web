package entity;

import utils.FieldValidations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "conferencepaperreference")
public class ConferencePaperReference extends Reference {

    @Column(name = "author")
    private String author;

    @Column(name = "booktitle")
    private String bookTitle;

    @Column(name = "editor")
    private String editor;

    @Column(name = "volume")
    private String volume;

    @Column(name = "numbera")
    private String number;

    @Column(name = "series")
    private String series;

    @Column(name = "pages")
    private String pages;

    @Column(name = "address")
    private String address;

    @Column(name = "organization")
    private String organization;

    @Column(name = "publisher")
    private String publisher;

    private final FieldValidations validations = new FieldValidations();

    public ConferencePaperReference() { }

    public ConferencePaperReference(String title, String year, String month, String note, String author, String bookTitle, String editor, String number, String series, String publisher, String volume, String address, String pages, String organization) {
        super(title, year, month, note);
        this.bookTitle = bookTitle;
        this.publisher = publisher;
        this.organization = organization;

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if (validations.validateNumber(number))
            this.number = number;

        if (validations.validateSeries(series))
            this.series = series;

        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if (validations.validateAddress(address))
            this.address = address;

        if (validations.validatePages(pages))
            this.pages = pages;
    }

    public ConferencePaperReference(Integer id, String title, String year, String month, String note, String author, String bookTitle, String editor, String number, String series, String publisher, String volume, String address, String pages, String organization) {
        super(id, title, year, month, note);
        this.bookTitle = bookTitle;
        this.publisher = publisher;
        this.organization = organization;

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if (validations.validateNumber(number))
            this.number = number;

        if (validations.validateSeries(series))
            this.series = series;

        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if (validations.validateAddress(address))
            this.address = address;

        if (validations.validatePages(pages))
            this.pages = pages;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) {
        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if (validations.validateAddress(address))
            this.address = address;
    }

    public String getPages() { return pages; }

    public void setPages(String pages) {
        if (validations.validatePages(pages))
            this.pages = pages;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if (validations.validateAuthorOrEditor(author))
            this.author = author;
    }

    public String getBookTitle() { return bookTitle; }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) {
        if (validations.validateNumber(number))
            this.number = number;
    }

    public String getSeries() { return series; }

    public void setSeries(String series) {
        if (validations.validateSeries(series))
            this.series = series;
    }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { this.organization = organization; }
}
