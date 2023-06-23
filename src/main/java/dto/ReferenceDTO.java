package dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

    protected String title;
    protected String year;
    protected String month;
    protected String note;
    protected Integer id;

    public ReferenceDTO() {}

    public ReferenceDTO(String title, String year, String month, String note, Integer id) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
        this.id = id;
    }

    public ReferenceDTO(String title, String year, String month, String note) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
}
