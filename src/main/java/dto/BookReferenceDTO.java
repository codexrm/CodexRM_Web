package dto;

public class BookReferenceDTO extends ReferenceDTO {

    protected String author;
    protected String editor;
    protected String publisher;
    protected String volume;
    protected String series;
    protected String number;
    protected String address;
    protected String edition;
    protected String isbn;

    public BookReferenceDTO() {}

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public BookReferenceDTO(String title, String year, String month, String note, Integer id, String author, String editor, String publisher, String volume, String series, String number, String address, String edition, String isbn) {
        super(title, year, month, note, id);
        this.author = author;
        this.editor = editor;
        this.publisher = publisher;
        this.volume = volume;
        this.series = series;
        this.number = number;
        this.address = address;
        this.edition = edition;
        this.isbn = isbn;
    }


    public BookReferenceDTO(String title, String year, String month, String note, String author, String editor, String publisher, String volume, String series, String number, String address, String edition, String isbn) {
        super(title, year, month, note);
        this.author = author;
        this.editor = editor;
        this.publisher = publisher;
        this.volume = volume;
        this.series = series;
        this.number = number;
        this.address = address;
        this.edition = edition;
        this.isbn = isbn;
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) { this.editor = editor; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
