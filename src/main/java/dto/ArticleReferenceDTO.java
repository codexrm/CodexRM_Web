package dto;

public class ArticleReferenceDTO extends ReferenceDTO {

    private String author;
    private String journal;
    private String volume;
    private String number;
    private String pages;
    private String issn;

    public ArticleReferenceDTO() {}

    public ArticleReferenceDTO(String title, String year, String month, String note, Integer id, String author, String journal, String volume, String number, String pages, String issn) {
        super(title, year, month, note, id);
        this.author = author;
        this.journal = journal;
        this.volume = volume;
        this.number = number;
        this.pages = pages;
        this.issn = issn;
    }

    public ArticleReferenceDTO(String title, String year, String month, String note, String author, String journal, String volume, String number, String pages, String issn) {
        super(title, year, month, note);
        this.author = author;
        this.journal = journal;
        this.volume = volume;
        this.number = number;
        this.pages = pages;
        this.issn = issn;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getIssn() { return issn; }

    public void setIssn(String issn) { this.issn = issn; }
}
