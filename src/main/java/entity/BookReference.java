package entity;

import utils.FieldValidations;

import javax.persistence.*;

@Entity
@Table(name = "bookreference")
@Inheritance(strategy = InheritanceType.JOINED)
public class BookReference extends Reference {

    @Column(name = "author")
    private String author;

    @Column(name = "editor")
    private String editor;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "volume")
    private String volume;

    @Column(name = "numbera")
    private String number;

    @Column(name = "series")
    private String series;

    @Column(name = "address")
    private String address;

    @Column(name = "edition")
    private String edition;

    @Column(name = "isbn")
    private String isbn;

    private final FieldValidations validations = new FieldValidations();

    public BookReference() {
    }

    public BookReference(String title, String year, String month, String note, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn) {
        super(title, year, month, note);
        this.publisher = publisher;

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if (validations.validateNumber(number))
            this.number = number;

        if (validations.validateSeries(series))
            this.series = series;

        if (validations.validateAddress(address))
            this.address = address;

        if (validations.validateEdition(edition))
            this.edition = edition;

        if (validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public BookReference(Integer id, String title, String year, String month, String note, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn) {
        super(id, title, year, month, note);
        this.publisher = publisher;

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if (validations.validateNumber(number))
            this.number = number;

        if (validations.validateSeries(series))
            this.series = series;

        if (validations.validateAddress(address))
            this.address = address;

        if (validations.validateEdition(edition))
            this.edition = edition;

        if (validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public String getEdition() { return edition; }

    public void setEdition(String edition) {
        if (validations.validateEdition(edition))
            this.edition = edition;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if (validations.validateAddress(address))
            this.address = address;
    }

    public String getSeries() { return series; }

    public void setSeries(String series) {
        if (validations.validateSeries(series))
            this.series = series;
    }

    public String getVolume() { return volume; }

    public void setVolume(String volume) {
        if (validations.validateChapterOrVolume(volume))
            this.volume = volume;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if (validations.validateAuthorOrEditor(author))
            this.author = author;
    }

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

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if (validations.validateIsbn(isbn))
            this.isbn = isbn;
    }
}