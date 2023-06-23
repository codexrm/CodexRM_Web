package dto;

public class WebPageReferenceDTO extends ReferenceDTO {

    private String author;
    private String url;

    public WebPageReferenceDTO() {}

    public WebPageReferenceDTO(String title, String year, String month, String note, Integer id, String author, String url) {
        super(title, year, month, note, id);
        this.author = author;
        this.url = url;
    }

    public WebPageReferenceDTO(String title, String year, String month, String note, String author, String url) {
        super(title, year, month, note);
        this.author = author;
        this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }
}
