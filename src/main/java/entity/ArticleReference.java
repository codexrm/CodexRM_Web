package entity;

import utils.FieldValidations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "articlereference")
public class ArticleReference extends Reference {

    @Column(name = "author")
    private String author;

    @Column(name = "journal")
    private String journal;

    @Column(name = "volume")
    private String volume;

    @Column(name = "numbera")
    private String number;

    @Column(name = "pages")
    private String pages;

    @Column(name = "issn")
    private String issn;

    private final FieldValidations validations = new FieldValidations();

    public ArticleReference() {}

    public ArticleReference(String title, String year, String month, String note, String author, String journal, String volume, String number, String pages, String issn) {
        super(title, year, month, note);
        this.journal = journal;

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.isNumber(volume))
        this.volume = volume;

        if(validations.validateNumber(number))
        this.number = number;

        if(validations.validatePages(pages))
        this.pages = pages;

        if(validations.validateIssn(issn))
        this.issn = issn;
    }

    public ArticleReference(Integer id, String title, String year, String month, String note, String author, String journal, String volume, String number, String pages, String issn) {
        super(id, title, year, month, note);
        this.journal = journal;

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.isNumber(volume))
            this.volume = volume;

        if(validations.validateNumber(number))
            this.number = number;

        if(validations.validatePages(pages))
            this.pages = pages;

        if(validations.validateIssn(issn))
            this.issn = issn;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getJournal() { return journal; }

    public void setJournal(String journal) { this.journal = journal; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) {
        if(validations.isNumber(volume))
        this.volume = volume;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) {
        if(validations.validateNumber(number))
        this.number = number;
    }

    public String getPages() { return pages; }

    public void setPages(String pages) {
        if(validations.validatePages(pages))
        this.pages = pages;
    }

    public String getIssn() { return issn; }

    public void setIssn(String issn) {
        if(validations.validateIssn(issn))
        this.issn = issn;
    }
}