package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.User;

import java.time.LocalDate;

public class WebPageReferenceDTO extends ReferenceDTO {

    private String url;
    private LocalDate accessDate;

    public WebPageReferenceDTO() {}

    public WebPageReferenceDTO(String author, String title, LocalDate date, String note, Integer id, User user, String url, LocalDate accessDate) {
        super(author, title, date, note, id, user);
        this.url = url;
        this.accessDate = accessDate;
    }

    public WebPageReferenceDTO(String author, String title, LocalDate date, String note, User user, String url, LocalDate accessDate) {
        super(author, title, date, note, user);
        this.url = url;
        this.accessDate = accessDate;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @JsonIgnore
    public LocalDate getAccessDateLocal() { return accessDate; }

    @JsonIgnore
    public void setAccessDateLocal(LocalDate accessDate) { this.accessDate = accessDate; }

    public String getAccessDate() {
        if (accessDate == null){
            return "0000-00-00";
        } else{
            return accessDate.toString();
        }
    }

    public void setAccessDate(String date) {
        if (date == null || date.equals("0000-00-00")) {
            this.accessDate = null;
        }else{
            String[] partDate = date.split("-", 3);
            this.accessDate = LocalDate.of(Integer.parseInt(partDate[0]),Integer.parseInt(partDate[1]),Integer.parseInt(partDate[2]));
        }
    }
}
