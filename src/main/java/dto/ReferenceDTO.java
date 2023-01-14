package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import entity.User;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = ArticleReferenceDTO.class, name = "articleDTO"),
        @JsonSubTypes.Type(value = BookReferenceDTO.class, name = "bookDTO"),
        @JsonSubTypes.Type(value = BookSectionReferenceDTO.class, name = "bookSectionDTO"),
        @JsonSubTypes.Type(value = ThesisReferenceDTO.class, name = "thesisDTO"),
        @JsonSubTypes.Type(value = BookLetReferenceDTO.class, name = "bookLetDTO"),
        @JsonSubTypes.Type(value = ConferencePaperReferenceDTO.class, name = "paperDTO"),
        @JsonSubTypes.Type(value = WebPageReferenceDTO.class, name = "webPageDTO"),
        @JsonSubTypes.Type(value = ConferenceProceedingsReferenceDTO.class, name = "proceedingsDTO")})
public class ReferenceDTO {

    protected String author;
    protected String title;
    protected LocalDate date;
    protected String note;
    protected Integer id;
    protected User user;

    public ReferenceDTO() {}

    public ReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.note = note;
        this.id = id;
        this.user = user;
    }

    public ReferenceDTO(String author, String title, LocalDate date, String note, User user) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.note = note;
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public LocalDate getLocalDate() {
        return date;
    }


    public void setLocalDate(LocalDate date) {
        this.date = date;
    }

    public String getDate() {
        if (date == null){
            return "0000-00-00";
        } else{
            return date.toString();
        }
    }

    public void setDate(String date) {
        if (date == null || date.equals("0000-00-00")) {
            this.date = null;
        }else{
            String[] partDate = date.split("-", 3);
            this.date = LocalDate.of(Integer.parseInt(partDate[0]),Integer.parseInt(partDate[1]),Integer.parseInt(partDate[2]));
        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
