package entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "webpagereference")
public class WebPageReference extends Reference{

    @Column(name = "access_date")
    private LocalDate accessDate;

    @Column(name = "url")
    private String url;

    public WebPageReference() {}

    public WebPageReference(String author, String title, LocalDate date, String note, User user, LocalDate accessDate, String url) {
        super(author, title, date, note, user);
        this.accessDate = accessDate;
        this.url = url;
    }

    public WebPageReference(int id, String author, LocalDate date, String note, String title, User user, LocalDate accessDate, String url) {
        super(id, author, date, note, title, user);
        this.accessDate = accessDate;
        this.url = url;
    }

    public LocalDate getAccessDate() { return accessDate; }

    public void setAccessDate(LocalDate accessDate) { this.accessDate = accessDate; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }
}
