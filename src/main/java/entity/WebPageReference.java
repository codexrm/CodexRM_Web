package entity;

import utils.FieldValidations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "webpagereference")
public class WebPageReference extends Reference {

    @Column(name = "author")
    private String author;

    @Column(name = "url")
    private String url;

    private final FieldValidations validations = new FieldValidations();

    public WebPageReference() {
    }

    public WebPageReference(String title, String year, String month, String note, String author, String url) {
        super(title, year, month, note);

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateUrl(url))
            this.url = url;
    }

    public WebPageReference(Integer id, String title, String year, String month, String note, String author, String url) {
        super(id, title, year, month, note);

        if (validations.validateAuthorOrEditor(author))
            this.author = author;

        if (validations.validateUrl(url))
            this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if (validations.validateAuthorOrEditor(author))
            this.author = author;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) {
        if (validations.validateUrl(url))
            this.url = url;
    }
}
