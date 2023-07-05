package entity;

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


    public WebPageReference() {}

    public WebPageReference(String title, String year, String month, String note, String author, String url) {
        super(title, year, month, note);
        this.author = author;
        this.url = url;
    }

    public WebPageReference(Integer id, String title, String year, String month, String note, String author, String url) {
        super(id, title, year, month, note);
        this.author = author;
        this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }
}
